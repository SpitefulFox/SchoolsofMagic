package fox.spiteful.schoolsmagic.psionics;

import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import thaumcraft.api.aura.AuraHelper;
import thaumcraft.common.entities.monster.EntityWisp;
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
import java.util.Random;

public class PieceTrickMoveNode extends PieceTrick {

    public static final double MULTIPLIER = 0.3;

    SpellParam target;
    SpellParam position;
    SpellParam speed;
    SpellParam stability;

    public PieceTrickMoveNode(Spell spell) {
        super(spell);
    }

    @Override
    public void initParams() {
        addParam(target = new ParamVector("psi.spellparam.target", SpellParam.BLUE, false, false));
        addParam(position = new ParamVector("psi.spellparam.position", SpellParam.GREEN, false, false));
        addParam(speed = new ParamNumber("psi.spellparam.speed", SpellParam.RED, false, true));
        addParam(stability = new ParamNumber("psi.spellparam.stability", SpellParam.PURPLE, true, true));
    }

    @Override
    public void addToMetadata(SpellMetadata meta) throws SpellCompilationException {
        super.addToMetadata(meta);
        Double speedVal = this.<Double>getParamEvaluation(speed);
        Double stabVal = this.<Double>getParamEvaluation(stability);
        if(speedVal == null)
            speedVal = 1D;
        if(stabVal == null)
            stabVal = 0D;

        double absSpeed = Math.abs(speedVal);
        double abstability = Math.abs(stabVal);
        meta.addStat(EnumSpellStat.POTENCY, (int) addSafe(multiplySafe(absSpeed,  absSpeed, 5.0), multiplySafe(abstability, abstability, 5.0)));
        meta.addStat(EnumSpellStat.COST, (int) addSafe(multiplySafe(absSpeed, Math.max(1, absSpeed), 100), multiplySafe(abstability, abstability, 100)));
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
        Double stabVal = this.<Double>getParamValue(context, stability);
        if(stabVal == null)
            stabVal = 0D;

        addMotion(context, targetVal, directionVal, speedVal);
        if(!context.caster.worldObj.isRemote && context.caster.worldObj.rand.nextInt(15) > stabVal){
            AuraHelper.pollute(context.caster.worldObj, targetVal.getPosition(), 1, true);
        }
        if(!context.caster.worldObj.isRemote && context.caster.worldObj.rand.nextInt(10) > stabVal){
            EntityWisp wisp = new EntityWisp(context.caster.worldObj);
            wisp.setLocationAndAngles(targetVal.posX, targetVal.posY, targetVal.posZ, 0.0F, 0.0F);
            wisp.setType(((EntityAuraNode)targetVal).getAspectTag());
            context.caster.worldObj.spawnEntityInWorld(wisp);
        }

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

    public double addSafe(double v1, double... arr) throws SpellCompilationException {
        double a = v1;
        for(int i = 0; i < arr.length; i++) {
            double b = arr[i];
            a = a + b;
            if((int) a < 0 || (int) a == Integer.MAX_VALUE)
                throw new SpellCompilationException(SpellCompilationException.STAT_OVERFLOW);
        }
        return a;
    }

}