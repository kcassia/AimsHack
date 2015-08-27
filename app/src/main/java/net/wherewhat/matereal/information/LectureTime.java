package net.wherewhat.matereal.information;


/**
 * Created by 계현 on 2015-08-05.
 */
public class LectureTime
{
    private int startTime;
    private int dayOfWeek;
    private int runningTime;
    private String lectureRoom;

    public LectureTime(int startTime, int dayOfWeek, int runningTime, String lectureRoom)
    {
        this.startTime = startTime;
        this.dayOfWeek = dayOfWeek;
        this.runningTime = runningTime;
        this.lectureRoom = lectureRoom;
    }

    public int getStartTime() {return startTime;}
    public int getDayOfWeek() {return dayOfWeek;}
    public int getRunningTime() {return runningTime;}
    public String getLectureRoom() {return lectureRoom;}
    public int getEndTime(){return startTime + runningTime;}


    public boolean isAdjacentWith(LectureTime lectureTime)
    {
        final int minimumLectureGap = 15;

        if (this.dayOfWeek != lectureTime.dayOfWeek)
            return false;

        if(!this.getLectureRoom().equals(lectureTime.getLectureRoom()))
            return false;

        if (this.isOverlappedWith(lectureTime))
            return true;

        if ((this.startTime >= lectureTime.getEndTime()) &&
                ((this.startTime - lectureTime.getEndTime()) <= minimumLectureGap))
            return true;
        else if ((this.getEndTime() <= lectureTime.startTime) && ((lectureTime.startTime - this.getEndTime()) <= minimumLectureGap))
            return true;
        return false;
    }

    public boolean isOverlappedWith(LectureTime lectureTime)
    {
        if (this.dayOfWeek != lectureTime.dayOfWeek) {
            return false;
        }

        if (this.startTime < lectureTime.getEndTime() &&
                this.getEndTime() >= lectureTime.getEndTime()) {

            return true;
        } else if (this.startTime <= lectureTime.startTime &&
                this.getEndTime() > lectureTime.startTime) {

            return true;
        } else if(this.startTime >= lectureTime.startTime &&
                this.getEndTime() <= lectureTime.getEndTime()){

            return true;
        }
        return false;
    };

}
