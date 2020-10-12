import java.util.*;

public class Helper {
   public static <E extends SimpleKey> E search(Collection<E> collection, String key) {
      for (E e : collection) {
         if (e.getKey().equals(key)) {
            return e;
         }
      }
      return null;
   }

}