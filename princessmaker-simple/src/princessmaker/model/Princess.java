package princessmaker.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Princess {
    /* 이 클래스는 Princess의 정보를 저장하는 클래스 */
    // 필드
    private String name;
    private int physical;
    private int intelligence;
    private int charm;
    private LocalDate currentDate;
    private Job job;
//    private int currentMonth;
    // 생성자
    public Princess(String name) {
        this.name = name;
        this.physical = 0;  // 각 수치는 0으로 시작
        this.intelligence = 0;
        this.charm = 0;
        this.currentDate = LocalDate.of(2025, 1, 1);
        this.job = null;    // 처음엔 직업 없음
//        this.currentMonth = currentDate.getMonthValue();
    }
    // 필요한 메서드
    // 한 달을 추가하는 메서드
    public void addMonth(){
//        currentMonth++;
        setCurrentDate(currentDate.plusMonths(1));
    }
    // currentDate를 String타입 yy/MM형식으로 변환하는 메서드
    public String getFormattedDate() {
        String formatter = currentDate.format(DateTimeFormatter.ofPattern("yy년MM월"));
        return formatter;
    }
    // amount만큼 해당 수치를 증가시키는 메서드
    public int increasePhysical(int amount) {
        return this.physical += amount;
    }
    public int increaseIntelligence(int amount) {
        return this.intelligence += amount;
    }
    public int increaseCharm(int amount) {
        return this.charm += amount;
    }

    // getter,setter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPhysical() {
        return physical;
    }
    public void setPhysical(int physical) {
        this.physical = physical;
    }
    public int getIntelligence() {
        return intelligence;
    }
    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }
    public int getCharm() {
        return charm;
    }
    public void setCharm(int charm) {
        this.charm = charm;
    }
    public LocalDate getCurrentDate() {
        return currentDate;
    }
    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }
    public Job getJob() {
        return job;
    }
    public void setJob(Job job) {
        this.job = job;
    }
//    public int getCurrentMonth() {
//        return currentMonth;
//    }
//    public void setCurrentMonth(int currentMonth) {
//        this.currentMonth = currentMonth;
//    }
}
