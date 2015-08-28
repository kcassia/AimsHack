package net.wherewhat.matereal.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import net.wherewhat.matereal.information.Lecture;
import net.wherewhat.matereal.R;
import net.wherewhat.matereal.activity.WebViewActivity;

import java.util.ArrayList;


/**
 * Implementation of App Widget functionality.
 */
public class TimeTableWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        // There may be multiple widgets active, so update all of them
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
        }

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        AppWidgetManager mgr = AppWidgetManager.getInstance(context);
            int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
            int viewIndex = intent.getIntExtra("TimeTableWidget", 0);

        super.onReceive(context, intent);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

//        String response = "{\"code\":200,\"message\":null,\"result\":{\"timetable\":{\"semester\":\"15-2\",\"lectureList\":["
//                + "{\"id\":2672,\"name\":\"SW창업론\",\"professor\":\"유승화\",\"major\":\"컴퓨터공학전공(과)\",\"credit\":3,\"lectureCode\":\"F037\",\"lectureTimeSet\":"
//                + "[{\"lectureId\":2672,\"dayOfWeek\":0,\"time\":990,\"runningTime\":90,\"lectureRoom\":\"팔410\",\"endTime\":1080},"
//                + "{\"lectureId\":2672,\"dayOfWeek\":2,\"time\":990,\"runningTime\":90,\"lectureRoom\":\"팔410\",\"endTime\":1080}]},"
//                + "{\"id\":2699,\"name\":\"종합설계프로젝트\",\"professor\":\"곽진\",\"major\":\"컴퓨터공학전공(과)\",\"credit\":4,\"lectureCode\":\"F023\",\"lectureTimeSet\":"
//                + "[{\"lectureId\":2699,\"dayOfWeek\":2,\"time\":720,\"runningTime\":90,\"lectureRoom\":\"팔325\",\"endTime\":810},"
//                + "{\"lectureId\":2699,\"dayOfWeek\":2,\"time\":810,\"runningTime\":90,\"lectureRoom\":\"팔333\",\"endTime\":900},"
//                + "{\"lectureId\":2699,\"dayOfWeek\":0,\"time\":720,\"runningTime\":90,\"lectureRoom\":\"팔325\",\"endTime\":810}]},"
//                + "{\"id\":2667,\"name\":\"컴퓨터비젼\",\"professor\":\"김동윤\",\"major\":\"컴퓨터공학전공(과)\",\"credit\":3,\"lectureCode\":\"F039\",\"lectureTimeSet\":"
//                + "[{\"lectureId\":2667,\"dayOfWeek\":3,\"time\":630,\"runningTime\":90,\"lectureRoom\":\"팔409\",\"endTime\":720},"
//                + "{\"lectureId\":2667,\"dayOfWeek\":0,\"time\":630,\"runningTime\":90,\"lectureRoom\":\"팔409\",\"endTime\":720}]},"
//                + "{\"id\":176,\"name\":\"공업수학A\",\"professor\":\"정은경\",\"major\":\"정보컴퓨터공학과\",\"credit\":3,\"lectureCode\":\"F001\",\"lectureTimeSet\":"
//                + "[{\"lectureId\":176,\"dayOfWeek\":1,\"time\":990,\"runningTime\":90,\"lectureRoom\":\"성306\",\"endTime\":1080},"
//                + "{\"lectureId\":176,\"dayOfWeek\":3,\"time\":900,\"runningTime\":90,\"lectureRoom\":\"성306\",\"endTime\":990}]},"
//                + "{\"id\":846,\"name\":\"동양고전철학\",\"professor\":\"홍성기\",\"major\":\"영역별교양\",\"credit\":3,\"lectureCode\":\"X319\",\"lectureTimeSet\":"
//                + "[{\"lectureId\":846,\"dayOfWeek\":0,\"time\":900,\"runningTime\":90,\"lectureRoom\":\"성332\",\"endTime\":990},"
//                + "{\"lectureId\":846,\"dayOfWeek\":2,\"time\":900,\"runningTime\":90,\"lectureRoom\":\"성332\",\"endTime\":990}]},"
//                + "{\"id\":826,\"name\":\"현대사회의 윤리\",\"professor\":\"이숙희\",\"major\":\"영역별교양\",\"credit\":3,\"lectureCode\":\"X025\",\"lectureTimeSet\":"
//                + "[{\"lectureId\":826,\"dayOfWeek\":3,\"time\":720,\"runningTime\":90,\"lectureRoom\":\"성132\",\"endTime\":810},"
//                + "{\"lectureId\":826,\"dayOfWeek\":1,\"time\":810,\"runningTime\":90,\"lectureRoom\":\"성132\",\"endTime\":900}]}]}}}";

        String response = "{\"code\":200,\"message\":null,\"result\":{\"timetable\":{\"semester\":\"15-2\",\"lectureList\":[" +
                "{\"id\":747,\"name\":\"아주강좌2\",\"professor\":\"주철환\",\"major\":\"교양과목\",\"credit\":1,\"lectureCode\":\"X047\",\"lectureTimeSet\":[" +
                "{\"lectureId\":747,\"dayOfWeek\":3,\"time\":990,\"runningTime\":60,\"lectureRoom\":\"종합101\",\"endTime\":1050}," +
                "{\"lectureId\":747,\"dayOfWeek\":3,\"time\":1050,\"runningTime\":60,\"lectureRoom\":\"종합101\",\"endTime\":1110}]}," +
                "{\"id\":2659,\"name\":\"데이터마이닝\",\"professor\":\"손경아\",\"major\":\"컴퓨터공학전공(과)\",\"credit\":3,\"lectureCode\":\"F041\",\"lectureTimeSet\":[" +
                "{\"lectureId\":2659,\"dayOfWeek\":2,\"time\":900,\"runningTime\":90,\"lectureRoom\":\"팔325\",\"endTime\":990}," +
                "{\"lectureId\":2659,\"dayOfWeek\":0,\"time\":900,\"runningTime\":90,\"lectureRoom\":\"팔325\",\"endTime\":990}]}," +
                "{\"id\":2672,\"name\":\"SW창업론\",\"professor\":\"유승화\",\"major\":\"컴퓨터공학전공(과)\",\"credit\":3,\"lectureCode\":\"F037\",\"lectureTimeSet\":[" +
                "{\"lectureId\":2672,\"dayOfWeek\":0,\"time\":990,\"runningTime\":90,\"lectureRoom\":\"팔410\",\"endTime\":1080}," +
                "{\"lectureId\":2672,\"dayOfWeek\":2,\"time\":990,\"runningTime\":90,\"lectureRoom\":\"팔410\",\"endTime\":1080}]}," +
                "{\"id\":787,\"name\":\"사회봉사실천2\",\"professor\":\"김춘아\",\"major\":\"교양과목\",\"credit\":1,\"lectureCode\":\"X003\",\"lectureTimeSet\":[" +
                "{\"lectureId\":787,\"dayOfWeek\":0,\"time\":0,\"runningTime\":0,\"lectureRoom\":null,\"endTime\":0}]}," +
                "{\"id\":2667,\"name\":\"컴퓨터비젼\",\"professor\":\"김동윤\",\"major\":\"컴퓨터공학전공(과)\",\"credit\":3,\"lectureCode\":\"F039\",\"lectureTimeSet\":[" +
                "{\"lectureId\":2667,\"dayOfWeek\":3,\"time\":630,\"runningTime\":90,\"lectureRoom\":\"팔409\",\"endTime\":720}," +
                "{\"lectureId\":2667,\"dayOfWeek\":0,\"time\":630,\"runningTime\":90,\"lectureRoom\":\"팔409\",\"endTime\":720}]}," +
                "{\"id\":2607,\"name\":\"시스템프로그래밍\",\"professor\":null,\"major\":\"컴퓨터공학전공(과)\",\"credit\":4,\"lectureCode\":\"F066\",\"lectureTimeSet\":[" +
                "{\"lectureId\":2607,\"dayOfWeek\":4,\"time\":810,\"runningTime\":60,\"lectureRoom\":\"팔318\",\"endTime\":870}," +
                "{\"lectureId\":2607,\"dayOfWeek\":4,\"time\":870,\"runningTime\":60,\"lectureRoom\":\"팔318\",\"endTime\":930}," +
                "{\"lectureId\":2607,\"dayOfWeek\":3,\"time\":900,\"runningTime\":90,\"lectureRoom\":\"팔410\",\"endTime\":990}," +
                "{\"lectureId\":2607,\"dayOfWeek\":1,\"time\":990,\"runningTime\":90,\"lectureRoom\":\"팔410\",\"endTime\":1080}]}," +
                "{\"id\":1351,\"name\":\"기술과 사회\",\"professor\":\"최정철\",\"major\":\"영역별교양\",\"credit\":3,\"lectureCode\":\"X065\",\"lectureTimeSet\":[" +
                "{\"lectureId\":1351,\"dayOfWeek\":3,\"time\":720,\"runningTime\":90,\"lectureRoom\":\"율258\",\"endTime\":810}," +
                "{\"lectureId\":1351,\"dayOfWeek\":1,\"time\":810,\"runningTime\":90,\"lectureRoom\":\"율258\",\"endTime\":900}]}]}}}";

        ArrayList<Lecture> lectures = TimeTableMaker.parseResponse(response);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.time_table);
        views.removeAllViews(R.layout.time);
        ComponentName timeTableActivity = new ComponentName(context, WebViewActivity.class);
        Intent intent = new Intent();
        intent.setComponent(timeTableActivity);
        intent.putExtra("lectures", lectures);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.time_table, pendingIntent);

        RemoteViews[] times = new RemoteViews[156];
        for(int i=0; i<times.length; i++)
            times[i] = new RemoteViews(context.getPackageName(), R.layout.time);

        TimeTableMaker.initialize(views, context);

        for(int i=0; i<lectures.size(); i++)
            TimeTableMaker.setLectureTime(times, lectures.get(i), context);

        TimeTableMaker.showLectureTime(views, times);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }


}

