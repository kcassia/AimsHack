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

        String response = "{\"code\":200,\"message\":null,\"result\":{\"timetable\":{\"semester\":\"16-1\",\"lectureList\":[" +
                "{\"id\":747,\"name\":\"데이터베이스\",\"professor\":\"변광준\",\"major\":\"소프트웨어및컴퓨터공학전공(과)\",\"credit\":3,\"lectureCode\":\"X077\",\"lectureTimeSet\":[" +
                "{\"lectureId\":747,\"dayOfWeek\":1,\"time\":720,\"runningTime\":90,\"lectureRoom\":\"팔409\",\"endTime\":810}," +
                "{\"lectureId\":747,\"dayOfWeek\":3,\"time\":720,\"runningTime\":90,\"lectureRoom\":\"팔409\",\"endTime\":810}]}," +
                "{\"id\":2659,\"name\":\"오픈소스SW입문\",\"professor\":\"null\",\"major\":\"소프트웨어및컴퓨터공학전공(과)\",\"credit\":3,\"lectureCode\":\"X572\",\"lectureTimeSet\":[" +
                "{\"lectureId\":2659,\"dayOfWeek\":3,\"time\":990,\"runningTime\":60,\"lectureRoom\":\"팔325\",\"endTime\":1050}," +
                "{\"lectureId\":2659,\"dayOfWeek\":3,\"time\":1050,\"runningTime\":60,\"lectureRoom\":\"팔325\",\"endTime\":1110}," +
                "{\"lectureId\":2659,\"dayOfWeek\":3,\"time\":1110,\"runningTime\":60,\"lectureRoom\":\"팔325\",\"endTime\":1170}]}," +
                "{\"id\":2672,\"name\":\"SW캡스톤디자인\",\"professor\":\"손태식\",\"major\":\"소프트웨어및컴퓨터공학전공(과)\",\"credit\":6,\"lectureCode\":\"X578\",\"lectureTimeSet\":[" +
                "{\"lectureId\":2672,\"dayOfWeek\":0,\"time\":900,\"runningTime\":90,\"lectureRoom\":\"팔325\",\"endTime\":990}," +
                "{\"lectureId\":2672,\"dayOfWeek\":0,\"time\":990,\"runningTime\":60,\"lectureRoom\":\"팔334\",\"endTime\":1050}," +
                "{\"lectureId\":2672,\"dayOfWeek\":0,\"time\":1050,\"runningTime\":60,\"lectureRoom\":\"팔334\",\"endTime\":1110}," +
                "{\"lectureId\":2672,\"dayOfWeek\":0,\"time\":1110,\"runningTime\":60,\"lectureRoom\":\"팔334\",\"endTime\":1170}," +
                "{\"lectureId\":2672,\"dayOfWeek\":2,\"time\":900,\"runningTime\":90,\"lectureRoom\":\"팔325\",\"endTime\":990}," +
                "{\"lectureId\":2672,\"dayOfWeek\":2,\"time\":990,\"runningTime\":60,\"lectureRoom\":\"팔334\",\"endTime\":1050}," +
                "{\"lectureId\":2672,\"dayOfWeek\":2,\"time\":1050,\"runningTime\":60,\"lectureRoom\":\"팔334\",\"endTime\":1110}," +
                "{\"lectureId\":2672,\"dayOfWeek\":2,\"time\":1110,\"runningTime\":60,\"lectureRoom\":\"팔334\",\"endTime\":1170}]}," +
                "{\"id\":1351,\"name\":\"컴퓨터비젼\",\"professor\":\"김동윤\",\"major\":\"소프트웨어및컴퓨터공학전공(과)\",\"credit\":3,\"lectureCode\":\"X016\",\"lectureTimeSet\":[" +
                "{\"lectureId\":1351,\"dayOfWeek\":2,\"time\":810,\"runningTime\":90,\"lectureRoom\":\"팔409\",\"endTime\":900}," +
                "{\"lectureId\":1351,\"dayOfWeek\":4,\"time\":810,\"runningTime\":90,\"lectureRoom\":\"팔409\",\"endTime\":900}]}," +
                "{\"id\":2607,\"name\":\"사회봉사실천2\",\"professor\":김춘아,\"major\":\"교양과목\",\"credit\":1,\"lectureCode\":\"X017\",\"lectureTimeSet\":[]}," +
                "{\"id\":1351,\"name\":\"사회봉사이론\",\"professor\":\"김춘아\",\"major\":\"교양과목\",\"credit\":1,\"lectureCode\":\"X016\",\"lectureTimeSet\":[" +
                "{\"lectureId\":1351,\"dayOfWeek\":1,\"time\":990,\"runningTime\":90,\"lectureRoom\":\"성135\",\"endTime\":1080}]}]}}}";

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

