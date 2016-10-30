package Astar;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/*
 * 
 * 
 * 건들지 마라 이거 자료 구조 임 
 * 
 * 이거 건들면 좆되다 
 * 
 */



public class MinHeap {
	private final ArrayList<Data> queue = new ArrayList<>();

	private int endPnt = 0;

	private final Map<String, Data> index = new HashMap<>();

	public Data getAndRemoveMin() {
		if (isEmpty()) {
			return null;
		}

		Data head = queue.get(0);
		
		System.out.println(" 현제 포인트 "+head.point.x+","+head.point.y); // 디버그 
		
		Data last = queue.get(endPnt - 1);
		queue.set(0, last);
		endPnt--;
		index.remove(getKey(head.point));

		topDown();

		return head;
	}

	
	
	
	
	
	
	
	public Data find(Point pnt) {
		return index.get(getKey(pnt));
	}
	
	
	
	
	
	
	
	
	
	
	

	public void add(Data data) {
		if (queue.size() > endPnt) {
			queue.set(endPnt, data);
		} else {
			queue.add(data);
		}
		endPnt++;

		index.put(getKey(data.point), data);

		bottomUp();
	}

	
	
	
	
	
	
	
	
	
	public boolean isEmpty() {
		
		System.out.println(" heep 용량 " + endPnt);  // 다버그
		return endPnt <= 0;
	}

	
	
	
	
	
	
	
	private String getKey(Point pnt) {
		return String.format("%d-%d", pnt.x, pnt.y); // 거리 차이 
	}
	
	
	
	
	
	
	
	
	

	private void topDown() {
		for (int cur = 0; cur < endPnt;) {
			int left = 2 * cur + 1;
			int right = 2 * cur + 2;

			Data dc = queue.get(cur);
			Data dl = left < endPnt ? queue.get(left) : null;

			Data dr = right < endPnt ? queue.get(right) : null;

			int next = -1;
			Data dn = dc;
			if (dl != null && dl.f() < dn.f()) {
				next = left;
				dn = dl;
			}
			if (dr != null && dr.f() < dn.f()) {
				next = right;
				dn = dr;
			}

			if (next >= 0 && next < endPnt) {
				queue.set(next, dc);
				queue.set(cur, dn);
				cur = next;
			} else {
				break;
			}
		}

	}
	
	
	
	
	
	
	
	

	private void bottomUp() {
		
				
		for (int cur = endPnt - 1; cur >= 0;) {
			
			
			int parent = (cur - 1) / 2;         
			if (parent < 0) {
				break;
			}

			Data dc = queue.get(cur);
			Data dp = queue.get(parent);

			if (dc.f() < dp.f()) {
				queue.set(parent, dc);
				queue.set(cur, dp);
				cur = parent;
			} else {
				break;
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}