package princessmaker.view;

import princessmaker.model.Princess;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleView {
    Scanner sc = new Scanner(System.in);
    String lineSplitter = "────────────────────────────────────────";
    // 메서드
    // 환영메시지 출력
    public void showWelcome(){
        System.out.println(
                lineSplitter + lineSplitter + "\n" +
                "______        _                               ___  ___        _                \n" +
                "| ___ \\      (_)                              |  \\/  |       | |               \n" +
                "| |_/ / _ __  _  _ __    ___   ___  ___  ___  | .  . |  __ _ | | __  ___  _ __ \n" +
                "|  __/ | '__|| || '_ \\  / __| / _ \\/ __|/ __| | |\\/| | / _` || |/ / / _ \\| '__|\n" +
                "| |    | |   | || | | || (__ |  __/\\__ \\\\__ \\ | |  | || (_| ||   < |  __/| |   \n" +
                "\\_|    |_|   |_||_| |_| \\___| \\___||___/|___/ \\_|  |_/ \\__,_||_|\\_\\ \\___||_|   \n" +
                "                                                                               \n" +
                "                            PRESS ENTER TO START                           \n" +
                "                        ⓒ 2025. K.SunJoon L.Chanju Y.YeRam All rights reserved.");
        System.out.print(lineSplitter + lineSplitter);
        sc.nextLine();
        sc.nextLine();
    }
    // 이름받는 칸
    public String inputName(){
        System.out.print("\n\n"+lineSplitter + lineSplitter);
        System.out.print("\n\n\n\n\n\n\n\n\n\n\tPrincess의 이름을 설정해주세요 : ");
        String name = "";
        while (name == "") {
            name = sc.nextLine().trim();
            if(!name.isEmpty()) {
                return name;
            } else {
                System.out.print("\n" + lineSplitter + lineSplitter + "\n\n\n\n\n\n\n\t이름 없는 Princess는 존재할 수 없습니다.\n\n\n\tPrincess의 이름을 설정해주세요 : ");
            }
        }
        return name;
    }
    // INTRO
    public void showIntro(Princess princess){
        System.out.println(lineSplitter + lineSplitter);
        System.out.println("\n\n\n\n\t\t" + princess.getName() + "님, 환영합니다! 이제 게임을 시작합니다.\n\n\n\n" + "NOW LOADING...");
        for (int i = 1; i <= 8; i++) {
            System.out.print("=========>");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\n\n\n\n\n\n\n\n\n\n" + lineSplitter + lineSplitter + "\n\t\t\t\t\t\t\t [ PROLOGUE ]");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(princess.getName() + "은(는) 원래 왕국의 공주였습니다. 하지만 반역자들에 의해 왕과 왕비는 폐위되어 감옥에 갇히게 되었고, \n당신은 왕의 충직한 신하로서 아기였던 공주를 빼돌려 옆 왕국으로 도망쳤습니다.");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("당신은 공주의 신분을 숨기고 키우며 살아왔지만, 이제 2년 후면 그녀는 성인이 됩니다.");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("당신은 그녀의 미래를 당신 마음대로 좌지우지하는 것은 옳지 않다는 것을 알고 있습니다.");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("그렇기에 공주가 성인이 되면 그녀의 출생에 대한 진실을 알려주리라 결심했고, \n이후 성인이 된 그녀가 맞이할 미래를 위해 왕국의 공주로서 도움이 되는 능력을 기르게 하고 싶습니다.");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("그녀에게 필요하다고 생각되는 활동을 권유하여 미래에 대비하게 하세요.\n");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("\n<Enter>를 누르면 다음으로 진행됩니다");
        sc.nextLine();
        sc.nextLine();
    }

    // 활동 선택
    public int selectActivity(Princess princess, int choice){
        while(true){
            System.out.println("\n" + lineSplitter + lineSplitter + "\n");
            System.out.println("\t\t" + princess.getFormattedDate());
            System.out.println("\n\t\t" + "오늘은 어떤 활동을 하실건가요?");
            System.out.println("\n\t\t" + "1. 운동(체력↑↑, 지능↑)");
            System.out.println("\t\t" + "2. 공부(지능↑↑, 매력↑)");
            System.out.println("\t\t" + "3. 사교(매력↑↑, 체력↑)");
            System.out.print("\n\t\t" + "선택 : ");
            // 사용자가 Int 형식의 값을 넣지 않아 InputMismatchException이 발생한 경우 처리
            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                sc.nextLine();  // 버퍼에 남은것 제거
                choice = 0;     // choice를 정수로 초기화
            }
            if((choice == 1 || choice == 2 || choice == 3)) {
                break;
            } else {
                System.out.println("\n" + lineSplitter + lineSplitter);
                System.out.println("\n\n\n\n"+"\t\t\t\t입력은 숫자 1, 2, 3 만 입력 가능합니다!"+"\n\n\n\n");
                for (int i = 1; i <= 8; i++) {
                    System.out.print("■■■■■■■■■■");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("\n\n\n");
        }
    return choice;
    }
    // 활동 중 화면
    public void showActivityProgress(String activityName, String message) {
        System.out.println("\n" + lineSplitter + lineSplitter);
        System.out.println("\n\n\n\n\t\t" + message);
        System.out.print("\n\n\n\n\t\t"+"진행중");
        for (int i = 1; i <= 5; i++) {
            System.out.print(" .");
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    // 활동 결과
    public void showActivityResult(String stat1, int amount1, String stat2, int amount2, Princess princess) {
        System.out.println("\n" + lineSplitter + lineSplitter);
        System.out.println("\n\n\t\t\t\t  "+"대성공!!\n");
        System.out.println("\t\t  "+stat1 + "이(가) " + amount1 + " 상승했습니다!");
        System.out.println("\t\t  "+stat2 + "이(가) " + amount2 + " 상승했습니다!");
        System.out.println("\n\t\t"+"[체력 : " + princess.getPhysical() + "] [지력 : " + princess.getIntelligence() + "] [매력 : " + princess.getCharm() + "]" );
        System.out.print("\n[Enter를 누르시면 활동 선택 화면으로 넘어갑니다]");
        sc.nextLine();  // 버퍼 제거용
        sc.nextLine();
    }
    // 12개월 경과 후 직업선택
    public int selectJob(Princess princess) {
        System.out.println("\n" + lineSplitter + lineSplitter);
        System.out.println("\n\t\t12개월이 지났습니다!");
        System.out.println("\t\t"+princess.getName() + "의 직업을 선택해주세요.");
        System.out.println("\t\t"+"선택한 직업에 따라 활동 성과에 보정률이 붙습니다!");
        System.out.println("\n\t\t"+"1. 군인 (체력 보정 +50%)");
        System.out.println("\t\t"+"2. 학자 (지능 보정 +50%)");
        System.out.println("\t\t"+"3. 연예인 (매력 보정 +50%)");
        System.out.print("\n\t\t"+"번호를 선택하세요 : ");
        int sel = sc.nextInt();
        for (int i = 1; i <= 12; i++) {
            System.out.print("■■■■■■");
            if (i == 6) {
                try {
                    Thread.sleep(500);
                    System.out.print("  취 직 중  ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return sel;
    }
    public void showJobMessage(Princess princess) {
        System.out.println("\n\n" + lineSplitter + lineSplitter);
        System.out.println("\n\t\t" + "[직업선택 완료]");
        System.out.println("\n\n\t\t"+princess.getName() + "은(는) " + princess.getJob().getJobName() + "에 취직했다!");
        System.out.println("\n\t\t"+"이제부터 직업에 따른 능력치 상승률이 증가합니다!");
        System.out.print("\n\n[Enter를 누르시면 활동 선택 화면으로 넘어갑니다]");
        sc.nextLine();  // 버퍼 제거용
        sc.nextLine();
    }
    public void showEnding(String endingName, String message){
        System.out.println("게임 종료!");
        System.out.println("엔딩: " + endingName);
        System.out.println(message);
    }
    public void close(){
        sc.close();
    }
}
