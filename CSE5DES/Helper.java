import java.util.*;

public class Helper
{
   public static <E extends SimpleKey> E search(Collection<E> collection, String key)
   {
      for(E e: collection)
      {
         if( e.getKey().equals(key) )
         {
            return e;
         }
      }
      return null;
   }


   public static <E extends CompositeKey> E search(Collection <E> collection,
   																			List<String> key)
   {
      for(E e: collection)
      {
         if( e.getKey().equals(key))
         {
            return e;
         }
      }
      return null;
   }

   // Each element on a separate line
   //
   public static <E> String getDisplayList(List<E> list)
   {
		String s = new String();
		for(E each: list)
		{
			s = s + "\n\t" + each.toString();
		}
		return s;
	}


}