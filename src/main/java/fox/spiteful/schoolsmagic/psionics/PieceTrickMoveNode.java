package fox.spiteful.schoolsmagic.psionics;

import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import thaumcraft.common.lib.aura.EntityAuraNode;
import vazkii.psi.api.internal.MathHelper;
import vazkii.psi.api.internal.Vector3;
import vazkii.psi.api.spell.*;
import vazkii.psi.api.spell.param.ParamNumber;
import vazkii.psi.api.spell.param.ParamVector;
import vazkii.psi.api.spell.piece.PieceTrick;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PieceTrickMoveNode extends PieceTrick {

    public static final double MULTIPLIER = 0.3;

    SpellParam target;
    SpellParam position;
    SpellParam speed;

    public PieceTrickMoveNode(Spell spell) {
        super(spell);
    }

    @Override
    public void initParams() {
        addParam(target = new ParamVector("psi.spellparam.target", SpellParam.BLUE, false, false));
        addParam(position = new ParamVector("psi.spellparam.position", SpellParam.GREEN, false, false));
        addParam(speed = new ParamNumber("psi.spellparam.speed", SpellParam.RED, false, true));
    }

    @Override
    public void addToMetadata(SpellMetadata meta) throws SpellCompilationException {
        super.addToMetadata(meta);
        Double speedVal = this.<Double>getParamEvaluation(speed);
        if(speedVal == null)
            speedVal = 1D;

        double absSpeed = Math.abs(speedVal);
        meta.addStat(EnumSpellStat.POTENCY, (int) multiplySafe(absSpeed,  absSpeed, 20.0));
        meta.addStat(EnumSpellStat.COST, (int) multiplySafe(absSpeed, Math.max(1, absSpeed), 150));
    }

    @Override
    public Object execute(SpellContext context) throws SpellRuntimeException {
        Entity targetVal = null;
        Vector3 targetPos = this.<Vector3>getParamValue(context, target);
        List list = context.caster.worldObj.getEntitiesWithinAABB(EntityAuraNode.class, AxisAlignedBB.fromBounds(targetPos.x, targetPos.y, targetPos.z, targetPos.x, targetPos.y, targetPos.z).expand(32.0D, 32.0D, 32.0D));
        Entity closest = null;
        double distance = Double.MAX_VALUE;
        Iterator it = list.iterator();

        while(it.hasNext()) {
            Entity e = (Entity)it.next();
            if(!((EntityAuraNode)e).stablized) {
                double gap = MathHelper.pointDistanceSpace(targetPos.x, targetPos.y, targetPos.z, e.posX, e.posY, e.posZ);
                if(gap < distance) {
                    distance = gap;
                    closest = e;
                }
            }
        }

        if(closest != null) {
            targetVal = closest;
        }
        else
            throw new SpellRuntimeException(SpellRuntimeException.NULL_TARGET);

        Vector3 directionVal = this.<Vector3>getParamValue(context, position).sub(Vector3.fromEntity(targetVal));
        Double speedVal = this.<Double>getParamValue(context, speed);

        addMotion(context, targetVal, directionVal, speedVal);

        return null;
    }

    public static void addMotion(SpellContext context, Entity e, Vector3 dir, double speed) throws SpellRuntimeException {
        context.verifyEntity(e);
        if(!context.isInRadius(e))
            throw new SpellRuntimeException(SpellRuntimeException.OUTSIDE_RADIUS);

        dir = dir.copy().normalize().multiply(MULTIPLIER * speed);

        String key = "psi:Entity" + e.getEntityId() + "Motion";

        if(Math.abs(dir.x) > 0.0001) {
            String keyv = key + "X";
            if(!context.customData.containsKey(keyv)) {
                e.motionX += dir.x;
                context.customData.put(keyv, 0);
            }
        }

        if(Math.abs(dir.y) > 0.0001) {
            String keyv = key + "Y";
            if(!context.customData.containsKey(keyv)) {
                e.motionY += dir.y;
                context.customData.put(keyv, 0);
            }

            if(e.motionY >= 0)
                e.fallDistance = 0;
        }

        if(Math.abs(dir.z) > 0.0001) {
            String keyv = key + "Z";
            if(!context.customData.containsKey(keyv)) {
                e.motionZ += dir.z;
                context.customData.put(keyv, 0);
            }
        }
    }

}