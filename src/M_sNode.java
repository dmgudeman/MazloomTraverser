public class M_sNode<E extends Comparable<? super E>>
{
   protected M_sNode<E> lftChild, rtChild;
   protected E data;
   protected M_sNode<E> myRoot;

   public M_sNode(E d, M_sNode<E> lft, M_sNode<E> rt)
   {
      lftChild = lft;
      rtChild = rt;
      data = d;
   }
}
