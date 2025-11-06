package princessmaker.service;

import princessmaker.model.Activity;
import princessmaker.model.Princess;

public class ActivityService {
    /* 이 클래스는 활동 실행과 능력치 계산을 담당한다 */
    // 생성자
    public ActivityService(){    }
    // 메서드
    public void executeActivity(Princess princess, Activity activity) {
        int primaryIncrease = (int)((Math.random() * 6) + 7);  // 7~12 까지 6개의 랜덤 숫자를 반환
        int secondaryIncrease = (int) ((Math.random() * 3) + 2);    // 2~4 까지의 3개의 랜덤 숫자를 반환
        // 직업 보정 적용
        if (princess.getJob() != null) {
            String bonusStat = princess.getJob().getBonusStat();
            String primaryStat = activity.getPrimaryStat();

            // 직업의 보너스 능력치와 활동의 주 능력치가 같으면 보너스 적용
            if (bonusStat.equals(primaryStat)) {
                double bonus = princess.getJob().getStatBonus();
                primaryIncrease = (int) (primaryIncrease * (1 + bonus));
            }
        }

        // 능력치 증가 적용
        String primaryStat = activity.getPrimaryStat();
        String secondaryStat = activity.getSecondaryStat();

        // 주 능력치 증가
        if (primaryStat.equals("체력")) {
            princess.increasePhysical(primaryIncrease);
        } else if (primaryStat.equals("지능")) {
            princess.increaseIntelligence(primaryIncrease);
        } else if (primaryStat.equals("매력")) {
            princess.increaseCharm(primaryIncrease);
        }

        // 부 능력치 증가
        if (secondaryStat.equals("체력")) {
            princess.increasePhysical(secondaryIncrease);
        } else if (secondaryStat.equals("지능")) {
            princess.increaseIntelligence(secondaryIncrease);
        } else if (secondaryStat.equals("매력")) {
            princess.increaseCharm(secondaryIncrease);
        }
    }

    public String getActivityMessage(Princess princess, Activity activity) {
        if (princess.getJob() != null) {
            return princess.getJob().getActivityMessage(activity.getActivityName());
        } else {
            return princess.getName() + "은(는) " + activity.getActivityName() + "을(를) 하는 중!";
        }
    }
}
