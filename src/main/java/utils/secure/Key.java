package utils.secure;

public enum Key {
    JAVA("java");
    private String code;

    Key(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
