package com.smileflowpig.money.moneyplatfrom.util;


import android.app.Dialog;
import android.content.Context;

import com.smileflowpig.money.common.factory.data.DataSource;
import com.smileflowpig.money.factory.bean.HongbaoBean;
import com.smileflowpig.money.factory.bean.HongbaoJson;
import com.smileflowpig.money.factory.bean.HongbaoStatusJson;
import com.smileflowpig.money.factory.bean.ReceiveHongbaoJson;
import com.smileflowpig.money.factory.data.helper.HongbaoHelper;
import com.smileflowpig.money.factory.util.LoginStatusUtil;
import com.smileflowpig.money.moneyplatfrom.weight.HongbaoDialog;
import com.smileflowpig.money.moneyplatfrom.weight.JieriDialog;

/**
 * 红包操作类
 */
public class HongbaoOperator {

    public static final int HONGBAO_REQUEST_CODE = 10011;
    public static final int HONGBAO_RESULT_CODE = 10012;
    public static final int HONGBAO_RESULT_LOGIN_CODE = 10013;
    public static final int JIERI_RESULT_LOGIN_CODE = 10014;


    //红包type
    public final int NEWPERSON = 1;
    public final int JIERI = 2;


    //钱
    private String money_newuser = null;
    private String money_jieri = null;

    //红包id
    private String newUser_id = null;
    private String jieri_id = null;


    private String newUser_msg = null;
    private String jieri_msg = null;

    private int currentType = 1;

    private static HongbaoOperator instance;

    private Context context;


    //是否显示新人红包
    private boolean isShowNewPerson = false;
    //是否显示节日红包
    private boolean isShowJieri = false;

    //判断用户是否领取过红包
    private boolean isGetUserHongbao = false;
    private boolean isGetjieriHongbao = false;


    private HongbaoJson novice;
    private HongbaoJson festical;

    //得到的钱
    private String newUser_getmoney = null;
    private String jieri_getmoney = null;

    private HongbaoOperator(Context context) {
        this.context = context;
    }

    public static HongbaoOperator getInstance(Context context) {
        if (instance == null)
            instance = new HongbaoOperator(context);

        return instance;
    }

    /**
     * 初始化
     */
    protected void init() {
        initNewPersonDialog();
        initJeiriDialog();
        showDia();
    }

    public void init(OnDialogChangeListener onDialogChangeListener) {
        this.onDialogChangeListener = onDialogChangeListener;
        getHongbaoMSG();

    }

    private void showDia() {
        if (isShowNewPerson) {
            hongbaoDialog.show();
        } else if (isShowJieri) {
            jieriDialog.show();
        }
    }

    private HongbaoDialog hongbaoDialog;

    private void initNewPersonDialog() {
        hongbaoDialog = new HongbaoDialog(context);
        hongbaoDialog.setNoOnclickListener(new HongbaoDialog.OnHongBaoClickListener() {
            @Override
            public void onCancle() {
                dismissNewPersonDialog();
            }

            @Override
            public void onHongbaoClicck() {
                currentType = NEWPERSON;
                if (!LoginStatusUtil.isLogin()) {
                    if (onDialogChangeListener != null) {
                        onDialogChangeListener.goToLogin();
                    }
                } else {
                    // TODO: 2018/11/20 领取红包
                    if (isGetUserHongbao) {
                        //直接跳转领取记录
                        onDialogChangeListener.goToRecord(novice.getSlogan(), novice.getSlogan(), newUser_getmoney, isGetUserHongbao);

                    } else {
                        //去领取红包
                        getHongbao(NEWPERSON);

                    }

                }


            }
        });
    }

    private JieriDialog jieriDialog;

    private void initJeiriDialog() {
        jieriDialog = new JieriDialog(context);
        jieriDialog.setNoOnclickListener(new JieriDialog.OnHongBaoClickListener() {
            @Override
            public void onCancle() {
                dismissJieriDialog();
            }

            @Override
            public void onHongbaoClicck() {
                currentType = JIERI;
                if (!LoginStatusUtil.isLogin()) {
                    if (onDialogChangeListener != null) {
                        onDialogChangeListener.goToLogin();
                    }
                } else {
                    // TODO: 2018/11/20 领取红包
                    if (isGetjieriHongbao) {
                        //直接跳转领取记录
                        onDialogChangeListener.goToRecord(festical.getSlogan(), festical.getSlogan(), jieri_getmoney, isGetjieriHongbao);

                    } else {
                        //去领取红包
                        getHongbao(JIERI);
                    }
                }
            }
        });
    }


    public OnDialogChangeListener onDialogChangeListener;


    public interface OnDialogChangeListener {


        void goToLogin();


        void showLoadding();

        void closeLoadding();


        void goToRecord(String title, String task_name, String money_count, boolean isGet);
    }


    public void dismissNewPersonDialog() {
        if (hongbaoDialog != null)
            hongbaoDialog.dismiss();
        if (isShowJieri) {
            if (jieriDialog != null)
                jieriDialog.show();
        }
    }


    public void dismissJieriDialog() {
        if (jieriDialog != null)
            jieriDialog.dismiss();
    }

    /**
     * 必须注册的
     *
     * @param requestCode
     * @param resultCode
     */
    public void OnActivityForResult(int requestCode, int resultCode) {

        if (requestCode == HONGBAO_REQUEST_CODE) {
            if (resultCode == HONGBAO_RESULT_LOGIN_CODE) {
                //新手红包登录之后的处理
                // TODO: 2018/11/20 获取新人红包
                dismissNewPersonDialog();
                switch (currentType) {
                    case NEWPERSON:
                        dismissNewPersonDialog();
                        ToastUtil.show(context, "登录成功获取新人红包");
                        checkGet(newUser_id, NEWPERSON, true);

                        break;
                    case JIERI:
                        dismissJieriDialog();
                        ToastUtil.show(context, "登录成功获取节日红包");
                        checkGet(jieri_id, JIERI, true);
                        break;
                }


            }

        }
    }

    /**
     * 获取红包开关
     */
    private void getHongbaoMSG() {
        HongbaoHelper.getHongbaoStatus(new DataSource.Callback<HongbaoBean>() {
            @Override
            public void onDataNotAvailable(int strRes) {

            }

            @Override
            public void onDataLoaded(HongbaoBean hongbaoBean) {
                if (hongbaoBean != null) {
                    novice = hongbaoBean.getNovice();
                    if (novice != null) {
                        if (novice.getStatus() == 1) {
                            isShowNewPerson = true;
                            newUser_id = novice.getId() + "";
                        }
                    }
                    festical = hongbaoBean.getFfestical();
                    if (festical != null && festical.getStatus() == 1) {
                        isShowJieri = true;
                        jieri_id = festical.getId() + "";
                    }
                    init();
                    checkIsGetStatus(false);
                }
            }
        });
    }


    /**
     * 判断用户是否领取过
     */
    private void checkIsGetStatus(boolean isGoToGet) {
        if (!LoginStatusUtil.isLogin()) {
            return;
        }
        if (newUser_id != null)
            checkGet(newUser_id, NEWPERSON, isGoToGet);
        if (jieri_id != null)
            checkGet(jieri_id, JIERI, isGoToGet);


    }


    /**
     * 判断是否领取过
     *
     * @param id
     * @param type
     * @param isGoToget 是否去领取
     */
    private void checkGet(String id, final int type, final boolean isGoToget) {
        HongbaoHelper.getUserHongbaoStatus(Integer.parseInt(id), new DataSource.Callback<HongbaoStatusJson>() {
            @Override
            public void onDataNotAvailable(int strRes) {

            }

            @Override
            public void onDataLoaded(HongbaoStatusJson hongbaoStatusJson) {
                if (hongbaoStatusJson != null) {
                    if (hongbaoStatusJson.getType() == 1) {
                        //领取过
                        if (type == NEWPERSON) {
                            isGetUserHongbao = true;
                            newUser_getmoney = hongbaoStatusJson.getMoney();
                        } else {
                            isGetjieriHongbao = true;
                            jieri_getmoney = hongbaoStatusJson.getMoney();
                        }
                        //是否跳转
                        if (isGoToget) {
                            //领取过跳转详情页
                            if (onDialogChangeListener != null) {
                                if (type == NEWPERSON) {
                                    onDialogChangeListener.goToRecord(novice.getSlogan(), novice.getSlogan(), hongbaoStatusJson.getMoney(), isGetUserHongbao);
                                } else {
                                    onDialogChangeListener.goToRecord(festical.getSlogan(), festical.getSlogan(), hongbaoStatusJson.getMoney(), isGetjieriHongbao);
                                }
                            }
                        }
                    } else {
                        if (type == NEWPERSON) {
                            isGetUserHongbao = false;

                        } else {
                            isGetjieriHongbao = false;
                        }
                        if (isGoToget) {
                            getHongbao(type);
                        }
                    }
                }

            }
        });
    }


    /**
     * 领取红包
     *
     * @param type
     */
    private void getHongbao(final int type) {
        if (onDialogChangeListener != null) {
            onDialogChangeListener.showLoadding();
        }
        HongbaoHelper.getReceiveHongbao(type == NEWPERSON ? newUser_id : jieri_id, new DataSource.Callback<ReceiveHongbaoJson>() {
            @Override
            public void onDataNotAvailable(int strRes) {
                if (onDialogChangeListener != null) {
                    onDialogChangeListener.closeLoadding();
                }
            }

            @Override
            public void onDataLoaded(ReceiveHongbaoJson receiveHongbaoJson) {
                if (onDialogChangeListener != null) {
                    onDialogChangeListener.showLoadding();
                }
                if (receiveHongbaoJson != null) {
                    if (onDialogChangeListener != null) {
                        onDialogChangeListener.goToRecord(receiveHongbaoJson.getName(), receiveHongbaoJson.getName(), receiveHongbaoJson.getMoney(), type == NEWPERSON ? isGetUserHongbao : isGetjieriHongbao);
                    }
                    if (type == NEWPERSON) {
                        dismissNewPersonDialog();
                    } else {
                        dismissJieriDialog();
                    }
                }
            }
        });

    }
}
