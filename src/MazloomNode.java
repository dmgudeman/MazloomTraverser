public class MazloomNode<E>
{

   protected MazloomNode<E> firstChild, sib, prev, myRoot;
   protected E data;

   public MazloomNode(E x, MazloomNode<E> s, MazloomNode<E> fc, MazloomNode<E> p)
   {
      data = x;
      sib = s;
      firstChild = fc;
      prev = p;
      myRoot = null;
   }

   public MazloomNode()
   {
      this(null, null, null, null);
   }

   public E getData()
   {
      return data;
   }

   protected MazloomNode(E x, MazloomNode<E> s, MazloomNode<E> fc,
         MazloomNode<E> p, MazloomNode<E> r)
   {
      this(x, s, fc, p);
      myRoot = r;
   }
}