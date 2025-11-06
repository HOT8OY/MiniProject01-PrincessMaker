package princessmaker.service;

import princessmaker.model.*;
import princessmaker.view.ConsoleView;

import java.util.Scanner;


public class GameService {
    // 각 객체 생성
    private Princess princess; // princess의 정보
    private ConsoleView view; // console 화면에 보여주는 GUI만
    private ActivityService activityService; // 활동 실행과 능력치 계산을 담당
    private Activity[] activities; // 활동(운동,공부,사교)의 정보
    // 객체 초기화
    public GameService() {
        this.view = new ConsoleView();
        this.activityService = new ActivityService();
        this.activities = new Activity[3]; // activities에서 선택된 배열에 따라 해당 Activity 실행.

        // 3가지 활동 초기화
        activities[0] = new Activity("운동", "체력", "지능");
        activities[1] = new Activity("공부", "지능", "매력");
        activities[2] = new Activity("사교", "매력", "체력");
    }
    /* =========================================================================================================== */
                                            /* 게임의 전체적인 진행을 24회 반복 */
                                            /* 각 내용의 진행은 메소드호출로 불러옴! */
    // 이름을 set한 후 24턴을 for문으로 반복
    public void startGame() {
        view.showWelcome();
        String name = view.inputName();

        princess = new Princess(name);      // !!! 프린세스에 사용자가 입력한 name으로 객체 생성 !!!
        view.showIntro(princess);

        // 24턴 진행
        for (int turn = 1; turn <= 24; turn++) {
            // 13턴일 때 직업 선택
            if (turn == 13) {
                selectJob();
            }

            // 활동 진행
            performActivity();
            princess.addMonth();
        }

        // 엔딩
        showEnding();
        view.close();
    }
    /* =========================================================================================================== */
                                            /* 활동 후 활동 결과까지 반환 메소드 */
    private void performActivity() {
        int choice = 0;
        choice = view.selectActivity(princess, choice);
        Activity activity = null;

        // 선택에 따라 활동 결정
        if (choice == 1) {
            activity = activities[0]; // 운동
        } else if (choice == 2) {
            activity = activities[1]; // 공부
        } else if (choice == 3) {
            activity = activities[2]; // 사교
        } else {
            System.out.println("\t\t"+"잘못된 선택입니다. 운동을 합니다.");
            activity = activities[0];
        }

        // 활동 메시지
        String message = activityService.getActivityMessage(princess, activity);
        view.showActivityProgress(activity.getActivityName(), message);

        // 능력치 증가 전 값 저장
        int oldPrimary = getStatValue(activity.getPrimaryStat());
        int oldSecondary = getStatValue(activity.getSecondaryStat());

        // 활동 실행
        activityService.executeActivity(princess, activity);

        // 증가량 계산
        int primaryIncrease = getStatValue(activity.getPrimaryStat()) - oldPrimary;
        int secondaryIncrease = getStatValue(activity.getSecondaryStat()) - oldSecondary;

        // 결과 출력
        view.showActivityResult(
                activity.getPrimaryStat(), primaryIncrease,
                activity.getSecondaryStat(), secondaryIncrease,
                princess
        );
    }
    /* =========================================================================================================== */
                                        /* 13턴 시작 시 Job을 선택하는 메소드 */
    private void selectJob() {
        Scanner sc = new Scanner(System.in);
        int choice = view.selectJob(princess);
        Job job = null;

        if (choice == 1) {
            job = new Soldier("군인", "체력");
        } else if (choice == 2) {
            job = new Scholar("학자", "지능");
        } else if (choice == 3) {
            job = new Celebrity("연애인", "매력");
        } else {
            job = new Soldier("군인", "체력");
            System.out.println("\n\n" + "────────────────────────────────────────────────────────────────────────────────");
            System.out.println("\n\n\t\t\t\t\t"+"[잘못된 선택]");
            System.out.println("\n\t\t"+"당신은 인생에서 되돌릴 수 없는 실수를 하였습니다!");
            System.out.println("\n\t\t\t\t\t"+"군인이 됩니다.");
            System.out.print("\n\n[Enter를 누르시면 다음 화면으로 넘어갑니다]");
            sc.nextLine();
            sc.nextLine();  // 버퍼 제거용
        }

        princess.setJob(job);
        view.showJobMessage(princess);
    }
    /* =========================================================================================================== */
                                        /* 최종 stat에 따라 엔딩을 출력하는 메소드 */
    private void showEnding() {
        String endingName;
        String message;

        if (princess.getPhysical() >= 150) {
            endingName = "장군 엔딩";
            message = princess.getName() + "은(는) 뛰어난 체력으로 장군이 되었다!";
        } else if (princess.getIntelligence() >= 150) {
            endingName = "교수 엔딩";
            message = princess.getName() + "은(는) 뛰어난 지능으로 교수가 되었다!";
        } else if (princess.getCharm() >= 150) {
            endingName = "스타 엔딩";
            message = princess.getName() + "은(는) 뛰어난 매력으로 스타가 되었다!";
        } else if (princess.getPhysical() >= 100 || princess.getIntelligence() >= 100 || princess.getCharm() >= 100) {
            endingName = "평범한 삶 엔딩";
            message = princess.getName() + "은(는) 평범하지만 행복한 삶을 살게 되었다.";
        } else {
            endingName = "백수 엔딩";
            message = princess.getName() + "은(는) 아무것도 하지 않고 백수가 되었다...";
        }

        view.showEnding(endingName, message);
    }
    /* =========================================================================================================== */
                                        /* 13턴 시작 시 Job을 선택하는 메소드 */
    private int getStatValue(String statName) {
        if (statName.equals("체력")) {
            return princess.getPhysical();
        } else if (statName.equals("지능")) {
            return princess.getIntelligence();
        } else if (statName.equals("매력")) {
            return princess.getCharm();
        }
        return 0;
    }
}
