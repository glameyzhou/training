package org.glamey.training.jvm.gc;

/**
 * 对象在GC时候的自我救赎
 * <p>
 * 测试逃逸相关的内容
 *
 * @author yang.zhou 2019.11.15.15
 */
public class FinalizeEscapeGC {

    public static FinalizeEscapeGC hook = null;


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize was executed");
        FinalizeEscapeGC.hook = null;
    }


    public static void main(String[] args) throws InterruptedException {
        hook = new FinalizeEscapeGC();

        //对象第一次救赎,救赎成功
        hook = null;
        System.gc();
        Thread.sleep(500); //finalize执行优先级比较低，我们做下休眠
        if (hook != null) {
            System.out.println("i am still alive");
        } else {
            System.out.println("I am dead");
        }


        //第二次救赎，但是失败。因为finalize方法只能被调用一次
        hook = null;
        System.gc();
        Thread.sleep(500); //finalize执行优先级比较低，我们做下休眠
        if (hook != null) {
            System.out.println("i am still alive");
        } else {
            System.out.println("I am dead");
        }

    }
}
