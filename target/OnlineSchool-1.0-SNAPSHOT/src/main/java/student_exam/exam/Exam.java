package student_exam.exam;

public class Exam implements Runnable {

    private final int timeExam;

    public Exam(int timeExam) {
        this.timeExam = timeExam;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Exam started ...");

        System.out.print("Time to finish - ");
        for (int i = timeExam; i > 0 ; i--) {
            System.out.print(i + "  ");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }



        }
        System.out.println('\n' + "End of time");
    }

}
