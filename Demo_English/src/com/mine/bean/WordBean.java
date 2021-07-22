package com.mine.bean;

/*
    表示出现的频率
 */
public class WordBean {

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public WordBean(String word, Integer num) {
        this.word = word;
        this.num = num;
    }

    private String word;
    private Integer num;

}
