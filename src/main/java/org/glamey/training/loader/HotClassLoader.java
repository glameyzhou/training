package org.glamey.training.loader;

import com.sun.tools.attach.AttachNotSupportedException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * https://www.ibm.com/developerworks/cn/java/j-lo-classloader/
 * http://blog.csdn.net/maosijunzi/article/details/45696589
 * http://blog.csdn.net/maosijunzi/article/details/45918477
 *
 * http://www.blogjava.net/heavensay/archive/2012/11/07/389685.html
 *
 * @author zhouyang.zhou, 2017.03.02.14.
 */
public class HotClassLoader {

  public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, IOException, AttachNotSupportedException {
    /*List<VirtualMachineDescriptor> virtualMachineDescriptors = VirtualMachine.list();
    for (VirtualMachineDescriptor virtualMachineDescriptor : virtualMachineDescriptors) {
      String displayName = virtualMachineDescriptor.displayName();
      String id = virtualMachineDescriptor.id();
      System.out.println(String.format("%s->%s", id, displayName));

      VirtualMachine attach = VirtualMachine.attach(id);
      attach.detach();
    }*/

    //打印当前的classLoader
    ClassLoader classLoader = HotClassLoader.class.getClassLoader();
    while (classLoader != null) {
      System.out.println(classLoader);
      classLoader = classLoader.getParent();
    }
  }
}
