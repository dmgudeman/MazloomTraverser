import java.util.NoSuchElementException;


public class M_sTree<E extends Comparable<? super E>> implements Cloneable
{
protected int mSize;
protected M_sNode<E> mRoot;

public M_sTree() {clear();}
public boolean empty() {return (mSize == 0);}

public void clear() {mSize = 0; mRoot = null;}

public int size() { return mSize; }

protected M_sNode<E> findMin( M_sNode<E> root)
{
   if (root == null)
      return null;
   if (root.lftChild == null)
      return root;
   return findMin(root.lftChild);
}
public E findMin() 
{
   if (mRoot == null)
      throw new NoSuchElementException();
   return findMin(mRoot).data;
   }
public E findMaz() {return null;}
}
