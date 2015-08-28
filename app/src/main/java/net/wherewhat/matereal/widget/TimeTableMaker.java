package net.wherewhat.matereal.widget;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.RemoteViews;

import net.wherewhat.matereal.information.Lecture;
import net.wherewhat.matereal.information.LectureTime;
import net.wherewhat.matereal.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * @author KyeHyun
 */
public class TimeTableMaker {

    private static final int COLOR_NUM = 8;
    private static int colorSelector = 0;

    public static void initialize(RemoteViews views, Context context) {

        views.setTextViewText(R.id.time_title_alphabet, "시간");
        views.setTextViewText(R.id.monday_title, "월");
        views.setTextViewText(R.id.tuesday_title, "화");
        views.setTextViewText(R.id.wednesday_title, "수");
        views.setTextViewText(R.id.thursday_title, "목");
        views.setTextViewText(R.id.friday_title, "금");
        views.setTextViewText(R.id.saturday_title, "토");
        views.setTextViewText(R.id.time_title_digit, "시간");

        views.setInt(R.id.time_title_alphabet, "setBackgroundColor", context.getResources().getColor(R.color.sky_blue));
        views.setInt(R.id.monday_title, "setBackgroundColor", context.getResources().getColor(R.color.sky_blue));
        views.setInt(R.id.tuesday_title, "setBackgroundColor", context.getResources().getColor(R.color.sky_blue));
        views.setInt(R.id.wednesday_title, "setBackgroundColor", context.getResources().getColor(R.color.sky_blue));
        views.setInt(R.id.thursday_title, "setBackgroundColor", context.getResources().getColor(R.color.sky_blue));
        views.setInt(R.id.friday_title, "setBackgroundColor", context.getResources().getColor(R.color.sky_blue));
        views.setInt(R.id.saturday_title, "setBackgroundColor", context.getResources().getColor(R.color.sky_blue));
        views.setInt(R.id.time_title_digit, "setBackgroundColor", context.getResources().getColor(R.color.sky_blue));

        views.setTextViewText(R.id.time_Z, "Z");
        views.setTextViewText(R.id.time_A, "A");
        views.setTextViewText(R.id.time_B, "B");
        views.setTextViewText(R.id.time_C, "C");
        views.setTextViewText(R.id.time_D, "D");
        views.setTextViewText(R.id.time_E, "E");
        views.setTextViewText(R.id.time_F, "F");
        views.setTextViewText(R.id.time_G, "G");
        views.setTextViewText(R.id.time_H, "H");

        views.setTextViewText(R.id.time_0, "0");
        views.setTextViewText(R.id.time_0_5, "0.5");
        views.setTextViewText(R.id.time_1, "1");
        views.setTextViewText(R.id.time_1_5, "1.5");
        views.setTextViewText(R.id.time_2, "2");
        views.setTextViewText(R.id.time_2_5, "2.5");
        views.setTextViewText(R.id.time_3, "3");
        views.setTextViewText(R.id.time_3_5, "3.5");
        views.setTextViewText(R.id.time_4, "4");
        views.setTextViewText(R.id.time_4_5, "4.5");
        views.setTextViewText(R.id.time_5, "5");
        views.setTextViewText(R.id.time_5_5, "5.5");
        views.setTextViewText(R.id.time_6, "6");
        views.setTextViewText(R.id.time_6_5, "6.5");
        views.setTextViewText(R.id.time_7, "7");
        views.setTextViewText(R.id.time_7_5, "7.5");
        views.setTextViewText(R.id.time_8, "8");
        views.setTextViewText(R.id.time_8_5, "8.5");
        views.setTextViewText(R.id.time_9, "9");
        views.setTextViewText(R.id.time_9_5, "9.5");
        views.setTextViewText(R.id.time_10, "10");
        views.setTextViewText(R.id.time_10_5, "10.5");
        views.setTextViewText(R.id.time_11, "11");
        views.setTextViewText(R.id.time_11_5, "11.5");
        views.setTextViewText(R.id.time_12, "12");
        views.setTextViewText(R.id.time_12_5, "12.5");
    }

    // Widget TimeTable 구성
    public static void setLectureTime(RemoteViews[] times, Lecture lecture, Context context) {
        ArrayList<LectureTime> lectureTimes = new ArrayList<>();
        Iterator<LectureTime> itr = lecture.getLectureTimeSet().iterator();
        while (itr.hasNext()) {
            LectureTime lectureTime = itr.next();
            if (lectureTime.getStartTime() >= 480 && lectureTime.getStartTime() <= 1230) {
                int index = getIndex(lectureTime.getStartTime(), lectureTime.getDayOfWeek());
                int numOfCell = lectureTime.getRunningTime() / 30;
                mergeLectureView(times, lecture, lectureTime, numOfCell, index, context);
                lectureTimes.add(lectureTime);
            }
        }
        ArrayList<LectureTime> mergedList = new ArrayList<>();
        for (int i = 0; i < lectureTimes.size() - 1; i++)
            for (int j = i + 1; j < lectureTimes.size(); j++)
                if (lectureTimes.get(i).isAdjacentWith(lectureTimes.get(j)))
                    mergedList.add(mergeLectureTime(lectureTimes.get(i), lectureTimes.get(j)));

        boolean mergeFlag;
        do {
            mergeFlag = false;
            for (int i = 0; i < mergedList.size() - 1; i++) {
                for (int j = i + 1; j < mergedList.size(); j++) {
                    if (mergedList.get(i).isAdjacentWith(mergedList.get(j))) {
                        mergedList.add(mergeLectureTime(mergedList.get(i), mergedList.get(j)));
                        mergedList.remove(j);
                        mergedList.remove(i);
                        mergeFlag = true;
                    }
                }
            }
        } while (mergeFlag);

        for (int i = 0; i < mergedList.size(); i++) {
            int index = getIndex(mergedList.get(i).getStartTime(), mergedList.get(i).getDayOfWeek());
            int numOfCell = mergedList.get(i).getRunningTime() / 30;
            mergeLectureView(times, lecture, mergedList.get(i), numOfCell, index, context);
        }
        colorSelector++;
    }

    private static int getIndex(int startTime, int dayOfWeek) {
        int index = (startTime - 480) / 30;
        switch (dayOfWeek) {
            case 5:
                index += 26;
            case 4:
                index += 26;
            case 3:
                index += 26;
            case 2:
                index += 26;
            case 1:
                index += 26;
        }
        return index;
    }

    public static void showLectureTime(RemoteViews views, RemoteViews[] times) {
        for (int i = 0; i < 26; i++)
            views.addView(R.id.monday, times[i]);
        for (int i = 26; i < 52; i++)
            views.addView(R.id.tuesday, times[i]);
        for (int i = 52; i < 78; i++)
            views.addView(R.id.wednesday, times[i]);
        for (int i = 78; i < 104; i++)
            views.addView(R.id.thursday, times[i]);
        for (int i = 104; i < 130; i++)
            views.addView(R.id.friday, times[i]);
        for (int i = 130; i < 156; i++)
            views.addView(R.id.saturday, times[i]);
    }

    // Lecture 객체 Merge
    private static LectureTime mergeLectureTime(LectureTime lectureTime1, LectureTime lectureTime2) {
        int startTime = lectureTime1.getStartTime() > lectureTime2.getStartTime() ?
                lectureTime2.getStartTime() : lectureTime1.getStartTime();
        int endTime = lectureTime1.getEndTime() > lectureTime2.getEndTime() ?
                lectureTime1.getEndTime() : lectureTime2.getEndTime();
        return new LectureTime(startTime, lectureTime1.getDayOfWeek(), endTime - startTime, lectureTime1.getLectureRoom());
    }

    // TextView Merge
    private static void mergeLectureView(RemoteViews[] times, Lecture lecture, LectureTime lectureTime, int numOfCell, int index, Context context) {
        int layout = 0;
        int viewId = 0;
        switch (numOfCell) {
            case 2:
                layout = R.layout.time_2;
                viewId = R.id.time_cell_2;
                break;
            case 3:
                layout = R.layout.time_3;
                viewId = R.id.time_cell_3;
                break;
            case 4:
                layout = R.layout.time_4;
                viewId = R.id.time_cell_4;
                break;
            case 6:
                layout = R.layout.time_6;
                viewId = R.id.time_cell_6;
                break;
            case 8:
                layout = R.layout.time_8;
                viewId = R.id.time_cell_8;
                break;
            case 9:
                layout = R.layout.time_9;
                viewId = R.id.time_cell_9;
                break;
            case 12:
                layout = R.layout.time_12;
                viewId = R.id.time_cell_12;
                break;
            case 14:
                layout = R.layout.time_14;
                viewId = R.id.time_cell_14;
                break;
            case 18:
                layout = R.layout.time_18;
                viewId = R.id.time_cell_18;
                break;
            case 20:
                layout = R.layout.time_20;
                viewId = R.id.time_cell_20;
                break;
            default:
        }
        times[index] = new RemoteViews(context.getPackageName(), layout);
        switch(colorSelector % COLOR_NUM)
        {
            case 0:
                times[index].setInt(viewId, "setBackgroundResource", R.drawable.shape_round_red);
                break;
            case 1:
                times[index].setInt(viewId, "setBackgroundResource", R.drawable.shape_round_blue);
                break;
            case 2:
                times[index].setInt(viewId, "setBackgroundResource", R.drawable.shape_round_yellow);
                break;
            case 3:
                times[index].setInt(viewId, "setBackgroundResource", R.drawable.shape_round_gray);
                break;
            case 4:
                times[index].setInt(viewId, "setBackgroundResource", R.drawable.shape_round_purple);
                break;
            case 5:
                times[index].setInt(viewId, "setBackgroundResource", R.drawable.shape_round_orange);
                break;
            case 6:
                times[index].setInt(viewId, "setBackgroundResource", R.drawable.shape_round_indigo);
                break;
            default: // 7
                times[index].setInt(viewId, "setBackgroundResource", R.drawable.shape_round_dark_gray);
        }
        times[index].setTextViewText(viewId, lecture.getName() + "\n" + lectureTime.getLectureRoom());
        times[index++].setTextColor(viewId, Color.WHITE);


        int tempNumOfCell = --numOfCell;
        int tempIndex = index;
        while (tempNumOfCell-- > 0)
            times[tempIndex++].setViewVisibility(R.id.time, View.GONE);
        tempNumOfCell = numOfCell;
        tempIndex = index;
        while (tempNumOfCell-- > 0)
            times[tempIndex++].setViewVisibility(R.id.time_cell_2, View.GONE);
        tempNumOfCell = numOfCell;
        tempIndex = index;

        while (tempNumOfCell-- > 0)
            times[tempIndex++].setViewVisibility(R.id.time_cell_3, View.GONE);
    }

    public static ArrayList<Lecture> parseResponse(String response) {
        StringTokenizer tokenizer = new StringTokenizer(response, ":[{}],\"");

        ArrayList<Lecture> lectures = new ArrayList<>();

        String name = null;
        String professor = null;
        String major = null;
        int credit = 0;
        String lectureCode = null;

        ArrayList<Integer> dayOfWeeks = new ArrayList<>();
        ArrayList<Integer> times = new ArrayList<>();
        ArrayList<Integer> runningTimes = new ArrayList<>();
        ArrayList<String> lectureRooms = new ArrayList<>();

        while (tokenizer.hasMoreTokens()) {
            String temp = tokenizer.nextToken();
            if (temp.equals("name")) {
                if (name != null) {
                    HashSet<LectureTime> lectureTimes = new HashSet<>();
                    for (int i = 0; i < dayOfWeeks.size(); i++)
                        lectureTimes.add(new LectureTime(times.get(i), dayOfWeeks.get(i), runningTimes.get(i), lectureRooms.get(i)));
                    lectures.add(new Lecture(name, professor, major, credit, lectureCode, lectureTimes));
                    dayOfWeeks.clear();
                    times.clear();
                    runningTimes.clear();
                    lectureRooms.clear();
                }
                name = tokenizer.nextToken();
            }
            else if (temp.equals("professor"))
                professor = tokenizer.nextToken();
            else if (temp.equals("major"))
                major = tokenizer.nextToken();
            else if (temp.equals("credit"))
                credit = Integer.parseInt(tokenizer.nextToken());
            else if (temp.equals("lectureCode"))
                lectureCode = tokenizer.nextToken();
            else if (temp.equals("dayOfWeek"))
                dayOfWeeks.add(Integer.parseInt(tokenizer.nextToken()));
            else if (temp.equals("time"))
                times.add(Integer.parseInt(tokenizer.nextToken()));
            else if (temp.equals("runningTime"))
                runningTimes.add(Integer.parseInt(tokenizer.nextToken()));
            else if (temp.equals("lectureRoom"))
                lectureRooms.add(tokenizer.nextToken());
        }
        HashSet<LectureTime> lectureTimes = new HashSet<>();
        for (int i = 0; i < dayOfWeeks.size(); i++)
            lectureTimes.add(new LectureTime(times.get(i), dayOfWeeks.get(i), runningTimes.get(i), lectureRooms.get(i)));
        lectures.add(new Lecture(name, professor, major, credit, lectureCode, lectureTimes));
        return lectures;
    }

}
