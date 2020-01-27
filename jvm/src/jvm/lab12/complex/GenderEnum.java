package jvm.lab12.complex;

public enum GenderEnum {
    Man(1),
    Women(2);

    private int code;

    GenderEnum(int code) { // Method "<init>":(Ljava/lang/String;II)V        // 例如： Man:枚举值 0:ordinate 1:code
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
