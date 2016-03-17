/* ListSorts.java */

import list.*;

public class ListSorts {

	private final static int SORTSIZE = 1000;

	/**
	 *  makeQueueOfQueues() makes a queue of queues, each containing one item
	 *  of q.  Upon completion of this method, q is empty.
	 *  @param q is a LinkedQueue of objects.
	 *  @return a LinkedQueue containing LinkedQueue objects, each of which
	 *    contains one object from q.
	 * @throws QueueEmptyException 
	 **/
	public static LinkedQueue makeQueueOfQueues(LinkedQueue q) throws QueueEmptyException  {
		// Replace the following line with your solution.
		LinkedQueue newQ = new LinkedQueue();
		if(q.isEmpty()){
			return null;
		}
		while(!q.isEmpty()){
			try{
				Object o = q.dequeue();
				LinkedQueue element = new LinkedQueue();
				element.enqueue(o);
				newQ.enqueue(element); 
			}
			catch(QueueEmptyException e){

			}
		}
		return newQ;
	}

	/**
	 *  mergeSortedQueues() merges two sorted queues into a third.  On completion
	 *  of this method, q1 and q2 are empty, and their items have been merged
	 *  into the returned queue.
	 *  @param q1 is LinkedQueue of Comparable objects, sorted from smallest 
	 *    to largest.
	 *  @param q2 is LinkedQueue of Comparable objects, sorted from smallest 
	 *    to largest.
	 *  @return a LinkedQueue containing all the Comparable objects from q1 
	 *   and q2 (and nothing else), sorted from smallest to largest.
	 **/
	public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) throws QueueEmptyException{
		// Replace the following line with your solution.
		if(q1.isEmpty() && q2.isEmpty()){
			return null;
		}
		if(q1.isEmpty()){
			return q2;
		}
		if(q2.isEmpty()){
			return q1;
		}
		LinkedQueue q = new LinkedQueue();
		try{
			while((!q1.isEmpty()) && (!q2.isEmpty())){
				Object o1 = q1.front();
				Object o2 = q2.front();
				if(((Comparable) o1).compareTo((Comparable)o2) <= 0 ){
					o1 = q1.dequeue();
					q.enqueue(o1);
				}else{
					o2 = q2.dequeue();
					q.enqueue(o2);
				}
			}
			if(q1.isEmpty()){
				Object o2 = q2.dequeue();
				q.enqueue(o2);
			}else{
				Object o1 = q1.dequeue();
				q.enqueue(o1);
			}
		}
		catch(QueueEmptyException e){
			
		}
		return q;
	}

	/**
	 *  partition() partitions qIn using the pivot item.  On completion of
	 *  this method, qIn is empty, and its items have been moved to qSmall,
	 *  qEquals, and qLarge, according to their relationship to the pivot.
	 *  @param qIn is a LinkedQueue of Comparable objects.
	 *  @param pivot is a Comparable item used for partitioning.
	 *  @param qSmall is a LinkedQueue, in which all items less than pivot
	 *    will be enqueued.
	 *  @param qEquals is a LinkedQueue, in which all items equal to the pivot
	 *    will be enqueued.
	 *  @param qLarge is a LinkedQueue, in which all items greater than pivot
	 *    will be enqueued.  
	 **/   
	public static void partition(LinkedQueue qIn, Comparable pivot, 
			LinkedQueue qSmall, LinkedQueue qEquals, 
			LinkedQueue qLarge) {
		// Your solution here.
		if(qIn.isEmpty()){
			return;
		}
		try{
			while(!qIn.isEmpty()){
				Object n = qIn.dequeue();
				if(((Comparable)n).compareTo(pivot) < 0){
					qSmall.enqueue(n);
				}else if(((Comparable)n).compareTo(pivot) < 0){
					qLarge.enqueue(n);
				}else{
					qEquals.enqueue(n);
				}
			}
		}catch(QueueEmptyException e){
			
		}
	}

	/**
	 *  mergeSort() sorts q from smallest to largest using mergesort.
	 *  @param q is a LinkedQueue of Comparable objects.
	 **/
	public static void mergeSort(LinkedQueue q) throws QueueEmptyException {
		// Your solution here
		if(q.isEmpty()){
			return;
		}
		try{
			q = makeQueueOfQueues(q);
			LinkedQueue i1, i2;
			while(q.size() != 1){
				i1 = (LinkedQueue)(q.dequeue());
				i2 = (LinkedQueue)(q.dequeue());
				i1 = mergeSortedQueues(i1, i2);
				q.enqueue(i1);
			}
			i1 =(LinkedQueue)(q.dequeue());
			q.append(i1);
		}catch(QueueEmptyException e){
			System.out.println("aaa");
		}
	}
	

	/**
	 *  quickSort() sorts q from smallest to largest using quicksort.
	 *  @param q is a LinkedQueue of Comparable objects.
	 **/
	public static void quickSort(LinkedQueue q) {
		// Your solution here.
	}

	/**
	 *  makeRandom() builds a LinkedQueue of the indicated size containing
	 *  Integer items.  The items are randomly chosen between 0 and size - 1.
	 *  @param size is the size of the resulting LinkedQueue.
	 **/
	public static LinkedQueue makeRandom(int size) {
		LinkedQueue q = new LinkedQueue();
		for (int i = 0; i < size; i++) {
			q.enqueue(new Integer((int) (size * Math.random())));
		}
		return q;
	}

	/**
	 *  main() performs some tests on mergesort and quicksort.  Feel free to add
	 *  more tests of your own to make sure your algorithms works on boundary
	 *  cases.  Your test code will not be graded.
	 * @throws QueueEmptyException 
	 **/
	public static void main(String [] args) throws QueueEmptyException {

		LinkedQueue q = makeRandom(10);
		System.out.println(q.toString());
		mergeSort(q);
		System.out.println(q.toString());

		q = makeRandom(10);
		System.out.println(q.toString());
		quickSort(q);
		System.out.println(q.toString());

		/* Remove these comments for Part III.
    Timer stopWatch = new Timer();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    mergeSort(q);
    stopWatch.stop();
    System.out.println("Mergesort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");

    stopWatch.reset();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    quickSort(q);
    stopWatch.stop();
    System.out.println("Quicksort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");
		 */
	}

}
