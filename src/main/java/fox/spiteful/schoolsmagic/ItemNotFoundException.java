package fox.spiteful.schoolsmagic;

public class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String mod, String item){
        super("Unable to find item " + item + " in mod " + mod + "! Are you using the correct version of the mod?");
    }
}
