import com.company.Main;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void maker() {
        assertEquals("testResources\\first.jpg 326.13\n", Main.maker("testResources\\first.jpg".split(" ")));
        assertEquals("testResources\\first.jpg 326.13 KB\n", Main.maker("-h testResources\\first.jpg".split(" ")));
        assertEquals("testResources\\first.jpg 333.96 KB\n", Main.maker("-h --si testResources\\first.jpg".split(" ")));
        assertEquals("333.24", Main.maker("-c testResources\\first.jpg testResources\\second.txt".split(" ")));
    }

    @Test
    public void sizeOfFile(){
        assertEquals(333959d, Main.sizeOfFile(new File("testResources\\first.jpg"), 1024, true), 0d);
        assertEquals(7277d, Main.sizeOfFile(new File("testResources\\second.txt"), 1024, true), 0d);
        assertEquals(682472d, Main.sizeOfFile(new File("testResources\\third"), 1024, true), 0d);
    }

    @Test
    public void humanSize() {
    assertEquals("1.0 KB", Main.humanSize(1024d, 1024d));
    assertEquals("1.0 MB", Main.humanSize(1024d * 1024d, 1024d));
    assertEquals("1.0 GB", Main.humanSize(1024d * 1024d * 1024d, 1024d));
    assertEquals("1.0 KB", Main.humanSize(1000d, 1000d));
    assertEquals("1.0 MB", Main.humanSize(1000d * 1000d, 1000d));
    assertEquals("1.0 GB", Main.humanSize(1000d * 1000d * 1000d, 1000d));
    }
}