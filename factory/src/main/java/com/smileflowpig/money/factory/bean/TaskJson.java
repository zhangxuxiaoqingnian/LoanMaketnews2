package com.smileflowpig.money.factory.bean;

public class TaskJson {


    /**
     * id : 4
     * product_id : 121
     * name : 完成申请注册成功任务
     * img : http://www.bangnidai.dev/Uploads/20170321/1490095621566541.png
     * type : 4
     * money : 5.00
     * start_time : 1542337591
     * end_time : 1542337591
     * status : 1
     * count_down :
     * create_time : 1542337591
     * update_time : 1542337591
     */

    private String id;
    private String product_id;
    private String name;
    private String img;
    private String type;
    private String money;
    private String start_time;
    private String end_time;
    private String status;
    private String count_down;
    private String create_time;
    private String update_time;

    private String user_id;
    private String task_id;


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    //任务类型
    private int task_type;

    //任务是否完成
    private boolean isComplete;


    //是不是标题
    private boolean isTitle = false;

    //是不是正在进行中
    private boolean isLoadding = false;

    private String titleContent ;


    public boolean isLoadding() {
        return isLoadding;
    }

    public void setLoadding(boolean loadding) {
        isLoadding = loadding;
    }

    public int getTask_type() {
        return task_type;
    }

    public void setTask_type(int task_type) {
        this.task_type = task_type;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public boolean isTitle() {
        return isTitle;
    }

    public void setTitle(boolean title) {
        isTitle = title;
    }

    public String getTitleContent() {
        return titleContent;
    }

    public void setTitleContent(String titleContent) {
        this.titleContent = titleContent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCount_down() {
        return count_down;
    }

    public void setCount_down(String count_down) {
        this.count_down = count_down;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}
