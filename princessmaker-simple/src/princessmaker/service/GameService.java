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
                                        /* 요구받은 statName의 stat을 반환하는 메소드 */
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
    /* =========================================================================================================== */
                                        /* 최종 stat에 따라 엔딩을 출력하는 메소드 */
    private void showEnding() {
        String endingName;
        String message;

        int physical = princess.getPhysical();
        int intelligence = princess.getIntelligence();
        int charm = princess.getCharm();
        int totalStats = physical + intelligence + charm;

        // 최고 능력치 엔딩 (200 이상)
        if (physical >= 200) {
            endingName = "⚔️전설의 용사 엔딩⚔️";
            message = princess.getName() + "은(는) 압도적인 체력으로 왕국 최강의 전사가 되었다!\n" +
                    "\t\t대륙을 위협하던 마왕을 홀로 물리치고 영웅으로 칭송받고 있다.\n" +
                    "\t\t이제 그녀의 이름만 들어도 적들은 두려움에 떨고 있다.";
        } else if (intelligence >= 200) {
            endingName = "🧙마법사 엔딩🧙";
            message = princess.getName() + "은(는) 초월적 지능으로 대마법사의 경지에 올랐다!\n" +
                    "\t\t고대 마법서를 모두 해독하고 새로운 마법 이론을 정립했다.\n" +
                    "\t\t각국의 왕들이 조언을 구하기 위해 줄을 서고 있다.";
        } else if (charm >= 200) {
            endingName = "👑여왕 엔딩👑";
            message = princess.getName() + "은(는) 카리스마로 왕국의 여왕으로 즉위했다!\n" +
                    "\t\t출생의 비밀이 밝혀지자 백성들은 열렬히 환호하며 그녀를 받아들였다.\n" +
                    "\t\t그녀의 지도 아래 왕국은 황금시대를 맞이하고 있다.";
        }
        // 높은 능력치 엔딩 (150 이상)
        else if (physical >= 150) {
            endingName = "⚔️장군 엔딩⚔️";
            message = princess.getName() + "은(는) 뛰어난 체력으로 왕국의 장군이 되었다!\n" +
                    "\t\t전장에서 보여준 용맹함은 병사들에게 큰 귀감이 되고 있다.\n" +
                    "\t\t그녀가 이끄는 부대는 연전연승을 거두며 왕국을 지키고 있다.";
        } else if (intelligence >= 150) {
            endingName = "👓교수 엔딩👓";
            message = princess.getName() + "은(는) 뛰어난 지능으로 왕립 아카데미의 교수가 되었다!\n" +
                    "\t\t그녀의 강의는 항상 만석이며 저서는 베스트셀러가 되었다.\n" +
                    "\t\t젊은 나이에 학계의 권위자로 인정받고 있다.";
        } else if (charm >= 150) {
            endingName = "⭐슈퍼스타 엔딩⭐";
            message = princess.getName() + "은(는) 압도적 매력으로 K-POP 슈퍼스타가 되었다!\n" +
                    "\t\t그녀의 공연 티켓은 1초 만에 매진되고 전 세계 팬들이 열광한다.\n" +
                    "\t\tSNS 팔로워 1억 명을 돌파하며 글로벌 아이콘이 되었다.";
        }
        // 복합 능력치 엔딩 (두 가지 능력이 모두 120 이상)
        else if (physical >= 120 && intelligence >= 120) {
            endingName = "🎯전술가 엔딩🎯";
            message = princess.getName() + "은(는) 힘과 지략을 겸비한 완벽한 전술가가 되었다!\n" +
                    "\t\t전장에서의 전략적 판단과 직접적인 전투 능력으로 적을 압도한다.\n" +
                    "\t\t왕국 최고의 군사 전문가로 인정받아 국방장관에 임명되었다.";
        } else if (intelligence >= 120 && charm >= 120) {
            endingName = "💼CEO 엔딩💼";
            message = princess.getName() + "은(는) 지성과 소통 능력으로 대기업 CEO가 되었다!\n" +
                    "\t\tAI 기반 스타트업을 창업해 단 3년 만에 유니콘 기업으로 성장시켰다.\n" +
                    "\t\t경제지 선정 '가장 영향력 있는 30세 이하 리더'로 선정되었다.";
        } else if (physical >= 120 && charm >= 120) {
            endingName = "🎬액션스타 엔딩🎬";
            message = princess.getName() + "은(는) 체력과 매력으로 할리우드 액션스타가 되었다!\n" +
                    "\t\t직접 스턴트를 소화하는 그녀의 영화는 흥행을 거듭하고 있다.\n" +
                    "\t\t넷플릭스 오리지널 시리즈 주연으로 에미상 후보에 올랐다.";
        }
        // 균형잡힌 능력치 엔딩 (모든 능력 100 이상)
        else if (physical >= 100 && intelligence >= 100 && charm >= 100) {
            endingName = "🌟만능 엔딩🌟";
            message = princess.getName() + "은(는) 모든 분야에서 균형잡힌 능력을 갖춘 인재가 되었다!\n" +
                    "\t\t외교관으로 활동하며 국제 무대에서 왕국의 위상을 높이고 있다.\n" +
                    "\t\t'르네상스형 인재'라는 평가를 받으며 다방면에서 활약 중이다.";
        }
        // 중간 능력치 엔딩 (한 가지 능력이라도 100 이상)
        else if (physical >= 100 || intelligence >= 100 || charm >= 100) {
            endingName = "💼평범한 삶 엔딩💼";
            message = princess.getName() + "은(는) 평범하지만 행복한 삶을 살게 되었다.\n" +
                    "\t\t안정적인 직장을 얻고 소소한 취미생활을 즐기며 지내고 있다.\n" +
                    "\t\t화려하진 않지만 자신만의 속도로 만족스러운 인생을 살고 있다.";
        }
        // 최하위 엔딩
        else {
            endingName = "🏠니트족 엔딩🏠";
            message = princess.getName() + "은(는) 집에서 게임과 넷플릭스만 보며 지내고 있다...\n" +
                    "\t\t'인생은 한 번뿐'이라며 열심히 살 필요가 없다고 주장하고 있다.\n" +
                    "\t\t하지만 가끔 창밖을 보며 '이래도 되는 걸까...' 하고 생각에 잠긴다.";
        }
        view.showEnding(endingName, message);
    }
}
