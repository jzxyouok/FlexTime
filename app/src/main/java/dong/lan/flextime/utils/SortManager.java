package dong.lan.flextime.utils;

import dong.lan.flextime.Config;
import dong.lan.flextime.bean.ToDoItem;

/**
 * 项目：FlexTime
 * 作者：梁桂栋
 * 日期： 2015/12/12  07:02.
 */
public class SortManager {
    public static double IMP = 3.5; //默认的重要性权重因子
    public static double URG = 2.5;//默认的紧急性权重因子
    public static int TAG = 1;
    public static String SORT_0=" 重要性 ×  重要因子 + 紧急性 × 紧急因子" ;
    public static String SORT_1=" ( 重要性 ×  重要因子 + 紧急性 × 紧急因子 ) × 事件效果";
    public static String SORT_2=" (( 重要性 ×  重要因子 + 紧急性 × 紧急因子 ) × 事件效果 ) × 时间比重";
    /*
    根据 紧急性 重要性 用户状态 设置权重因子
     */
    public static double getSortWeight(int imp, int urg, int status) {
        return (imp * IMP + URG * urg) * Config.getLevelFactor(status);
    }

    /*
    根据 Tag标签选择排序算法
     */
    public static double getSortWeight(ToDoItem toDoItem) {

        return sortByTag(TAG, toDoItem);

    }

    public static void init(double imp, double urg) {
        IMP = imp;
        URG = urg;
    }

    public static double sortByTag(int tag, ToDoItem toDoItem) {
        switch (tag) {
            case 0:
                return (toDoItem.getImportant() * IMP + URG * toDoItem.getUrgent());
            case 1:
                return (toDoItem.getImportant() * IMP + URG * toDoItem.getUrgent()) * Config.getLevelFactor(toDoItem.getStatus());
            case 2:
                double d = (toDoItem.getStartTime() - System.currentTimeMillis())/toDoItem.getNeedTime();
                return (toDoItem.getImportant() * IMP + URG * toDoItem.getUrgent()) * Config.getLevelFactor(toDoItem.getStatus()) * d;
            default:
                return (toDoItem.getImportant() * IMP + URG * toDoItem.getUrgent()) * Config.getLevelFactor(toDoItem.getStatus());
        }
    }
}
