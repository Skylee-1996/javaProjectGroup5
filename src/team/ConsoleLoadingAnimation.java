package team;


public class ConsoleLoadingAnimation {
    public static void main(String[] args) throws InterruptedException {
        int totalTasks = 10;
        
        System.out.println("로딩 중...");
        
        for (int i = 0; i < totalTasks; i++) {
            Thread.sleep(500); // 임의의 딜레이를 추가 (실제 작업을 대신)
            System.out.print("\r진행 상태: [" + repeatString("=", i) + repeatString(" ", totalTasks - i) + "]" + (i * 10) + "%");
        }
        
        System.out.println("\n로딩 완료!");
    }
    
    private static String repeatString(String str, int times) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < times; i++) {
            result.append(str);
        }
        return result.toString();
    }
}