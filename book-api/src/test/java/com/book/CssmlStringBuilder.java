package com.book;

public class CssmlStringBuilder {

    public enum Type {
        PHONEME("phoneme"),
        BREAK("break"),
        PUNCTUATION("punctuation"),
        SAYAS("sayas");

        private final String type;

        Type(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    // 文档：http://bbs.xfyun.cn/thread/15340

    // 指定文字读音 phoneme标签的属性  py和lang不能同时指定
    public final static String PHONEME_PY = "py";
    public final static String PHONEME_LANG = "lang";

    // 在文本合成时加入自定义的静音停顿 break标签的属性 可设置停顿时间 毫秒
    public final static String BREAK_TIME = "time";

    // 标点的朗读规则指定 break标签的属性 speak_out有三个取值yes,no,default。yes：读出，no：静默，default：自动判断
    public final static String PUNCTUATION_SPEAK_OUT = "speak_out";

    /*
     * 	数字读法示例：
     * 	1、现在时钟已指向<sayas type="number">8</sayas>
     *	2、我们一共有<sayas type="numberrdinal">13</sayas>个人
     *	3、我住在<sayas type="number:digits">412</sayas>房间
     *	4、双方比分是<sayas type="number:score">3:1</sayas>
     *	5、取其中的<sayas type="number:fraction">1/3</sayas>
     * */
    public final static String SAYAS_TYPE = "type";

    private final StringBuffer sb;

    public CssmlStringBuilder() {
        sb = new StringBuffer();
    }

    /**
     * 设置数字读法的Cssml
     * 不允许在设置的Cssml包含和读数无关的内容，否则将失效，如：
     * <sayas type="number:digits">412房间</sayas>
     *
     * @param text          文本
     * @param propertyValue 属性值
     * @return {@link CssmlStringBuilder}
     */
    public CssmlStringBuilder appendSayas(String propertyValue, String text) {
        return append(Type.SAYAS, SAYAS_TYPE, propertyValue, text);
    }

    public CssmlStringBuilder append(String text) {
        sb.append(text);
        return this;
    }

    public CssmlStringBuilder append(Type type, String property, String propertyValue, String text) {
        switch (type) {
            case PHONEME:
            case BREAK:
            case PUNCTUATION:
            case SAYAS:
                addCssml(type.getType(),property,propertyValue,text);
                break;
            default:
                return append(text);
        }
        return this;
    }

    private void addCssml(String type, String property, String propertyValue, String text){
        sb.append("<")
            .append(type)
            .append(" ".concat(property))
            .append("=\"")
            .append(propertyValue)
            .append("\">")
            .append(text)
            .append("</")
            .append(type)
            .append(">");
    }

    public String toString() {
        return sb.toString();
    }
}
