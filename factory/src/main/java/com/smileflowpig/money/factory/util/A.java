package com.smileflowpig.money.factory.util;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 小狼 on 2018/5/31.
 */

public class A {

    /**
     * rst : {"2":[{"id":131,"name":"2345贷款王","icon":"http://img.henhaojie.com/Uploads/20170321/1490098678688438.png"}],"A":[],"B":[],"C":[{"id":125,"name":"曹操贷","icon":"http://img.henhaojie.com/Uploads/20170321/1490096154381470.png"}],"D":[],"E":[],"F":[],"G":[],"H":[{"id":135,"name":"花无忧","icon":"http://img.henhaojie.com/Uploads/20170420/1492667880925545.jpg"}],"I":[],"J":[{"id":116,"name":"玖富叮当贷","icon":"http://img.henhaojie.com/Uploads/20170321/1490094388362107.jpg"},{"id":117,"name":"嘉卡贷-信用卡代还","icon":"http://img.henhaojie.com/Uploads/20170321/1490094670881729.png"},{"id":108,"name":"借得快","icon":"http://img.henhaojie.com/Uploads/20170321/1490093335905393.jpeg"}],"K":[{"id":122,"name":"快贷","icon":"http://img.henhaojie.com/Uploads/20170720/1500539515283654.jpg"},{"id":115,"name":"快贷","icon":"http://img.henhaojie.com/Uploads/20170321/1490094164293865.png"},{"id":132,"name":"快贷","icon":"http://img.henhaojie.com/Uploads/20170321/1490098018504057.png"},{"id":133,"name":"卡卡贷","icon":"http://img.henhaojie.com/Uploads/20170428/1493353112302349.png"}],"L":[],"M":[{"id":105,"name":"名校贷白领版","icon":"http://img.henhaojie.com/Uploads/20170321/1490092441426573.jpeg"},{"id":107,"name":"魔法现金","icon":"http://img.henhaojie.com/Uploads/20170321/1490093032176992.jpg"}],"N":[],"O":[],"P":[{"id":120,"name":"拍拍贷","icon":"http://img.henhaojie.com/Uploads/20170321/1490095217879678.png"},{"id":137,"name":"平安I贷","icon":"http://img.henhaojie.com/Uploads/20170526/1495789580891324.png"}],"Q":[{"id":111,"name":"钱多金服信贷家-大额贷","icon":"http://img.henhaojie.com/Uploads/20170321/1490093765607781.png"},{"id":112,"name":"钱多金服平安i贷","icon":"http://img.henhaojie.com/Uploads/20170321/1490093768576653.jpg"},{"id":128,"name":"钱站","icon":"http://img.henhaojie.com/Uploads/20170321/1490097092599785.png"},{"id":110,"name":"嗨钱-滴答贷","icon":"http://img.henhaojie.com/Uploads/20170321/1490093692816029.jpeg"},{"id":138,"name":"钱急送","icon":"http://img.henhaojie.com/Uploads/20170526/1495793017309932.png"}],"R":[],"S":[{"id":123,"name":"上班贷","icon":"http://img.henhaojie.com/Uploads/20170321/1490096023966156.png"}],"T":[],"U":[],"V":[],"W":[{"id":127,"name":"我来贷","icon":"http://img.henhaojie.com/Uploads/20170321/1490096855799909.png"}],"X":[{"id":119,"name":"现金卡","icon":"http://img.henhaojie.com/Uploads/20170321/1490095199780443.png"},{"id":129,"name":"小赢卡贷","icon":"http://img.henhaojie.com/Uploads/20170321/1490097298633166.png"},{"id":103,"name":"信而富","icon":"http://img.henhaojie.com/Uploads/20170321/1490085200550275.png"},{"id":124,"name":"现金借款","icon":"http://img.henhaojie.com/Uploads/20170321/1490096144392880.png"},{"id":106,"name":"信用宝贷款","icon":"http://img.henhaojie.com/Uploads/20170321/1490092972298587.jpeg"},{"id":109,"name":"现金巴士","icon":"http://img.henhaojie.com/Uploads/20170321/1490093405402418.jpg"},{"id":140,"name":"小赢卡贷","icon":"http://img.henhaojie.com/Uploads/20180411/1523414556267641.png"},{"id":141,"name":"小赢卡贷","icon":"http://img.henhaojie.com/Uploads/20180411/1523414584797336.png"}],"Y":[],"Z":[{"id":114,"name":"智能贷","icon":"http://img.henhaojie.com/Uploads/20170321/1490094787642939.png"}]}
     * errno : 0
     * err : 成功
     * timestamp : 1527772013
     */

    public RstBean rst;
    public String errno;
    public String err;
    public String timestamp;

    public static class RstBean {
        @SerializedName("2")
        public List<_$2Bean> _$2;
        public List<?> A;
        public List<?> B;
        public List<CBean> C;
        public List<?> D;
        public List<?> E;
        public List<?> F;
        public List<?> G;
        public List<HBean> H;
        public List<?> I;
        public List<JBean> J;
        public List<KBean> K;
        public List<?> L;
        public List<MBean> M;
        public List<?> N;
        public List<?> O;
        public List<PBean> P;
        public List<QBean> Q;
        public List<?> R;
        public List<SBean> S;
        public List<?> T;
        public List<?> U;
        public List<?> V;
        public List<WBean> W;
        public List<XBean> X;
        public List<?> Y;
        public List<ZBean> Z;

        public static class _$2Bean {
            /**
             * id : 131
             * name : 2345贷款王
             * icon : http://img.henhaojie.com/Uploads/20170321/1490098678688438.png
             */

            public int id;
            public String name;
            public String icon;
        }

        public static class CBean {
            /**
             * id : 125
             * name : 曹操贷
             * icon : http://img.henhaojie.com/Uploads/20170321/1490096154381470.png
             */

            public int id;
            public String name;
            public String icon;
        }

        public static class HBean {
            /**
             * id : 135
             * name : 花无忧
             * icon : http://img.henhaojie.com/Uploads/20170420/1492667880925545.jpg
             */

            public int id;
            public String name;
            public String icon;
        }

        public static class JBean {
            /**
             * id : 116
             * name : 玖富叮当贷
             * icon : http://img.henhaojie.com/Uploads/20170321/1490094388362107.jpg
             */

            public int id;
            public String name;
            public String icon;
        }

        public static class KBean {
            /**
             * id : 122
             * name : 快贷
             * icon : http://img.henhaojie.com/Uploads/20170720/1500539515283654.jpg
             */

            public int id;
            public String name;
            public String icon;
        }

        public static class MBean {
            /**
             * id : 105
             * name : 名校贷白领版
             * icon : http://img.henhaojie.com/Uploads/20170321/1490092441426573.jpeg
             */

            public int id;
            public String name;
            public String icon;
        }

        public static class PBean {
            /**
             * id : 120
             * name : 拍拍贷
             * icon : http://img.henhaojie.com/Uploads/20170321/1490095217879678.png
             */

            public int id;
            public String name;
            public String icon;
        }

        public static class QBean {
            /**
             * id : 111
             * name : 钱多金服信贷家-大额贷
             * icon : http://img.henhaojie.com/Uploads/20170321/1490093765607781.png
             */

            public int id;
            public String name;
            public String icon;
        }

        public static class SBean {
            /**
             * id : 123
             * name : 上班贷
             * icon : http://img.henhaojie.com/Uploads/20170321/1490096023966156.png
             */

            public int id;
            public String name;
            public String icon;
        }

        public static class WBean {
            /**
             * id : 127
             * name : 我来贷
             * icon : http://img.henhaojie.com/Uploads/20170321/1490096855799909.png
             */

            public int id;
            public String name;
            public String icon;
        }

        public static class XBean {
            /**
             * id : 119
             * name : 现金卡
             * icon : http://img.henhaojie.com/Uploads/20170321/1490095199780443.png
             */

            public int id;
            public String name;
            public String icon;
        }

        public static class ZBean {
            /**
             * id : 114
             * name : 智能贷
             * icon : http://img.henhaojie.com/Uploads/20170321/1490094787642939.png
             */

            public int id;
            public String name;
            public String icon;
        }
    }
}
