package prob2024afterStars;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob23b2after {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2024/prob2024in23.txt"));
			//in = new Scanner(new File("in2024/prob2024in0.txt"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;
			String line = "";

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			int LIMIT = 20000;
			boolean table342[][] = new boolean[LIMIT][LIMIT];
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
			}

			int most = 0;
			int most2 = 0;
			int most3 = 0;
			long cur = 0L;
			ArrayList ints = new ArrayList<Integer>();
			
			Hashtable<String, Integer> labelsToIndex = new Hashtable<String, Integer>();
			Hashtable<Integer, String> indexToLabel = new Hashtable<Integer, String>();
			
			int curIndex = 0;
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				String label1 = line.split("-")[0];
				String label2 = line.split("-")[1];
				
				if(labelsToIndex.containsKey(label1) == false) {
					labelsToIndex.put(label1, curIndex);
					indexToLabel.put(curIndex, label1);
					curIndex++;
				}
				
				if(labelsToIndex.containsKey(label2) == false) {
					labelsToIndex.put(label2, curIndex);
					indexToLabel.put(curIndex, label2);
					curIndex++;
				}
			}
			int numElements = labelsToIndex.size();
			boolean connections[][] = new boolean[numElements][numElements];
			
			
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				String label1 = line.split("-")[0];
				String label2 = line.split("-")[1];
				
				int index1 = labelsToIndex.get(label1);
				int index2 = labelsToIndex.get(label2);
				
				connections[index1][index2] = true;
				connections[index2][index1] = true;
			}
		
			
			ArrayList<String> triples = new ArrayList<String>();
			
			for(int i=0; i<numElements; i++) {
				
				for(int j=i+1; j<numElements; j++) {
					
					for(int k=j+1; k<numElements; k++) {
						
						if(indexToLabel.get(i).startsWith("t") || indexToLabel.get(j).startsWith("t") || indexToLabel.get(k).startsWith("t")) {
							
							if(connections[i][j] && connections[j][k] && connections[k][i]) {
								triples.add(i +"," + j + "," + k);
							}
							
						} else {
							continue;
						}
						
					}
				}
			}
			
			ArrayList <Integer> listBest = getBest(connections);
			
			boolean connections2[] = new boolean[connections.length];
			
			for(int i=0; i<listBest.size(); i++) {
				connections2[listBest.get(i)] = true;
			}
			
			String answer = getCurAnswer(connections2, indexToLabel);
			
			sopl("Answer: " + answer);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static ArrayList<Integer> getBest(boolean connections[][]) {
		
		ArrayList <Integer>curList = new ArrayList<Integer>();
		
		return getBest(curList, connections, 0, 0);
	}

	public static ArrayList<Integer> globalBest = new ArrayList<Integer>();
	
	public static ArrayList<Integer> getBest(ArrayList <Integer>curList, boolean connections[][], int startIndex, int depth) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		
		if(depth == 3) {
			for(int j=0; j<curList.size(); j++) {
				sop(curList.get(j) + ", ");
			}
			sopl();
			sopl();
		}
		
		for(int i=0; i<curList.size(); i++) {
			ret.add(curList.get(i));
		}
		
		ArrayList<Integer> tmpBest = null;
		for(int i=startIndex; i<connections.length; i++) {
			boolean good = true;
			
			for(int j=0; j<curList.size(); j++) {
				
				if(curList.get(j) == i || connections[curList.get(j)][i] == false) {
					good = false;
				}
				
			}
			
			if(good == true) {
				ret.add(i);
				ArrayList<Integer> tmp = getBest(ret, connections, i+1, depth + 1);
				
				if(tmp!= null) {
					if(tmpBest == null || tmp.size() > tmpBest.size()) {
						tmpBest = tmp;
					}
				}
				ret.remove(ret.size() - 1);
			}
			
		}
		
		if(tmpBest == null) {
			
			if(ret.size() > globalBest.size()) {
				globalBest = ret;
				
				sopl("Global best: ");
				for(int j=0; j<curList.size(); j++) {
					sop(curList.get(j) + ", ");
				}
				sopl();
				sopl();
			}
			return ret;
		}
		
		return tmpBest;
	}
	
	
	public static String getCurAnswer(boolean connections[], Hashtable<Integer, String> indexToLabel) {
		
		ArrayList<String> labels = new ArrayList<String>();
		for(int i=0; i<connections.length; i++) {
			if(connections[i]) {
				labels.add(indexToLabel.get(i));
			}
		}
		
		ArrayList<String> retOrder = new ArrayList<String>();
		
		for(int i=0; i<labels.size(); i++) {
			
			int nextIndex = i;
			for(int j=i+1; j<labels.size(); j++) {

				if(labels.get(nextIndex).compareTo(labels.get(j)) > 0) {
					nextIndex = j;
				}

			}

			retOrder.add(labels.get(nextIndex));
			
			String tmp1 = labels.get(i);
			String tmp2 = labels.get(nextIndex);
			
			labels.set(i, tmp2);
			labels.set(nextIndex, tmp1);
			

		}
		
		String ret = "";
		for(int i=0; i<retOrder.size(); i++) {
			ret += retOrder.get(i) + ",";
		}
		
		ret = ret.substring(0, ret.length() - 1);
		
		return ret;
	}
	
	//aa,ab,ac,ae,af,ai,aj,an,ao,ap,aq,as,at,au,av,aw,ax,ay,bb,bc,bd,bf,bg,bh,bi,bj,bk,bl,bm,bo,bp,br,bs,bt,bu,bv,bw,bx,by,bz,ca,cb,cc,cd,ce,cf,cg,ch,cj,cl,cm,cn,cp,cq,cr,cs,ct,cv,cx,cy,db,dc,dd,de,df,dg,dh,di,dj,dk,dl,dn,do,dq,dr,ds,dt,du,dv,dw,dx,dy,dz,ea,eb,ec,ed,ee,ef,eg,eh,ej,ek,el,em,en,eo,ep,eq,er,es,et,eu,ev,ew,ex,ey,ez,fa,fc,fd,fe,ff,fg,fh,fi,fk,fl,fm,fn,fo,fp,fq,fr,fs,ft,fu,fv,fw,fz,gc,ge,gf,gg,gi,gj,gk,gl,gm,gn,go,gp,gq,gr,gs,gu,gv,gw,gx,gy,gz,ha,he,hf,hg,hh,hi,hj,hk,hm,ho,hq,hr,hs,hu,hv,hw,hx,hy,hz,ia,ib,id,ie,ig,ih,ii,ij,ik,il,im,in,io,ir,is,it,iu,ix,iy,iz,ja,jb,jd,jf,jg,jh,ji,jk,jl,jn,jo,jq,jr,jt,ju,jv,jw,jx,jy,jz,ka,kc,kd,ke,kg,kh,kj,kl,kn,ko,kq,kr,kt,ku,kv,kw,kx,kz,la,lb,lc,le,lf,lh,lj,lk,ll,ln,lq,lr,ls,lt,lu,lv,lw,lx,ly,lz,ma,mb,md,mg,mh,mi,mj,mn,mo,mq,ms,mt,mw,mx,my,mz,na,nc,nd,ne,nf,nh,ni,nj,nl,nm,nn,no,np,nq,ns,nt,nv,nw,nx,ny,oa,ob,od,oe,of,og,oi,ok,ol,om,on,oo,op,oq,ou,ov,ow,ox,oy,oz,pb,pf,pg,ph,pi,pj,pl,po,pp,pq,pr,ps,pt,pv,pw,px,py,pz,qa,qb,qd,qe,qf,qg,qi,qj,qk,ql,qm,qn,qo,qp,qq,qr,qs,qu,qw,qx,qy,ra,rb,rc,re,rf,rg,ri,rj,rk,rm,rn,ro,rp,rq,rr,rs,rt,ru,rv,rw,rx,ry,rz,sa,sb,sd,se,sg,sh,si,sk,sm,sn,so,sp,sq,ss,sw,sx,sy,sz,ta,td,te,tf,tg,th,ti,tj,tk,tl,tm,tn,tq,tr,tt,tu,tw,tx,ty,tz,ub,ue,uf,ui,uj,uk,um,un,uo,up,uq,ur,us,ut,uu,uv,ux,uy,va,vb,vc,vd,vh,vi,vj,vk,vl,vm,vn,vo,vp,vq,vr,vs,vt,vv,vw,vx,vz,wa,wb,wc,wd,we,wf,wg,wi,wj,wl,wn,wo,wp,wq,wr,ws,wt,wu,wv,wz,xa,xc,xd,xe,xg,xh,xi,xj,xk,xl,xm,xn,xo,xp,xq,xr,xw,xx,xy,ya,yb,yc,yd,yf,yg,yh,yi,yk,ym,yn,yo,yp,yq,ys,yt,yv,yw,yx,yy,za,zb,zc,ze,zf,zh,zj,zm,zn,zp,zq,zr,zs,zt,zv,zw,zx,zy
	
	
	
	//aa,on,ab,za,ac,bp,ae,hs,af,vc,ai,bg,aj,tm,an,us,ao,zf,ap,ox,aq,ib,as,lc,at,uo,au,zs,av,ov,aw,qj,ax,ll,ay,eb,bb,iy,bc,tq,bd,by,bf,un,bg,ix,bh,gi,bi,do,bj,rv,bk,ou,bl,yp,bm,qu,bo,qb,bp,um,br,qs,bs,dn,bt,ru,bu,ro,bv,et,bw,fw,bx,yx,by,jt,bz,ns,ca,iz,cb,od,cc,rc,cd,ny,ce,ox,cf,vb,cg,fd,ch,wf,cj,wr,cl,yh,cm,qy,cn,fa,cp,ct,cq,qo,cr,fv,cs,ig,ct,jv,cv,qi,cx,ff,cy,nj,db,ii,dc,sa,dd,gg,de,jv,df,ez,dg,kh,dh,vs,di,du,dj,gp,dk,pt,dl,my,dn,jf,do,sz,dq,yo,dr,gm,ds,fn,dt,xw,du,iu,dv,xr,dw,se,dx,mw,dy,ie,dz,qs,ea,xy,eb,qw,ec,wi,ed,xc,ee,yo,ef,qo,eg,tw,eh,zj,ej,ka,ek,ib,el,go,em,fn,en,vi,eo,fa,ep,pz,eq,kz,er,py,es,nd,et,ws,eu,og,ev,lw,ew,lb,ex,hj,ey,qo,ez,um,fa,qi,fc,qa,fd,sq,fe,xk,ff,lu,fg,qa,fh,rv,fi,zh,fk,zh,fl,tg,fm,nm,fn,sp,fo,uo,fp,ke,fq,vs,fr,ut,fs,ly,ft,gr,fu,vr,fv,ko,fw,qg,fz,xg,gc,um,ge,up,gf,jt,gg,it,gi,gz,gj,vi,gk,lw,gl,mw,gm,xn,gn,hi,go,pq,gp,th,gq,kr,gr,gw,gs,gu,gu,sm,gv,zq,gw,ke,gx,pz,gy,kq,gz,rw,ha,qo,he,ib,hf,tw,hg,zw,hh,xm,hi,ho,hj,vi,hk,oy,hm,hy,ho,wi,hq,hw,hr,qr,hs,jh,hu,vh,hv,xy,hw,vz,hx,ja,hy,yk,hz,nm,ia,yi,ib,yf,id,kx,ie,py,ig,sz,ih,qq,ii,um,ij,zh,ik,jt,il,jk,im,xl,in,jk,io,jf,ir,my,is,sh,it,op,iu,vx,ix,jl,iy,tl,iz,yo,ja,wn,jb,zv,jd,yn,jf,th,jg,wd,jh,wj,ji,ns,jk,tw,jl,ql,jn,ls,jo,mz,jq,qs,jr,ov,jt,ov,ju,xn,jv,po,jw,of,jx,nv,jy,yx,jz,jz,ka,nl,kc,ze,kd,wj,ke,vd,kg,rn,kh,ov,kj,nl,kl,vb,kn,nd,ko,ws,kq,no,kr,yv,kt,yh,ku,yn,kv,vo,kw,xo,kx,td,kz,vq,la,ow,lb,vm,lc,yo,le,lf,lf,oq,lh,rp,lj,np,lk,yh,ll,op,ln,ow,lq,qn,lr,qe,ls,mw,lt,rc,lu,rf,lv,om,lw,sa,lx,vh,ly,rr,lz,yn,ma,xg,mb,tn,md,xg,mg,yw,mh,nd,mi,my,mj,wo,mn,rz,mo,wa,mq,sx,ms,ym,mt,oe,mw,sw,mx,pq,my,nf,mz,sq,na,zt,nc,tg,nd,ny,ne,tg,nf,uo,nh,wd,ni,wc,nj,wq,nl,vw,nm,ux,nn,nq,no,wf,np,qx,nq,uy,ns,ti,nt,qm,nv,tj,nw,nw,nx,pj,ny,rs,oa,qb,ob,sn,od,sy,oe,ur,of,zv,og,ov,oi,ok,ok,ps,ol,qr,om,ym,on,us,oo,pw,op,qu,oq,uu,ou,vd,ov,zs,ow,za,ox,vh,oy,ri,oz,ry,pb,ri,pf,wr,pg,tg,ph,ss,pi,za,pj,wv,pl,yx,po,zj,pp,ym,pq,uq,pr,uo,ps,sh,pt,uq,pv,qi,pw,qe,px,tm,py,si,pz,xa,qa,ym,qb,sx,qd,te,qe,um,qf,rg,qg,uv,qi,sw,qj,xj,qk,tr,ql,zp,qm,qo,qn,rx,qo,xa,qp,yw,qq,sh,qr,yc,qs,zn,qu,sq,qw,wi,qx,yp,qy,wl,ra,tt,rb,zh,rc,rs,re,re,rf,zw,rg,wu,ri,rv,rj,rt,rk,rq,rm,zy,rn,tu,ro,ry,rp,vd,rq,zp,rr,yv,rs,sn,rt,xr,ru,vx,rv,zb,rw,ut,rx,zp,ry,vv,rz,xa,sa,ya,sb,wa,sd,vi,se,te,sg,wl,sh,us,si,wv,sk,wc,sm,ta,sn,yd,so,yk,sp,vx,sq,ty,ss,uu,sw,wv,sx,zy,sy,ya,sz,xp,ta,ws,td,yf,te,tw,tf,tw,tg,uk,th,un,ti,zw,tj,wb,tk,yc,tl,vk,tm,vj,tn,tr,tq,uj,tr,vr,tt,zp,tu,vl,tw,ty,tx,wn,ty,uf,tz,wz,ub,vx,ue,xe,uf,ys,ui,zn,uj,zc,uk,zw,um,yb,un,xo,uo,vd,up,yf,uq,wp,ur,uu,us,xo,ut,vh,uu,yd,uv,wb,ux,zt,uy,wt,va,ze,vb,ya,vc,wq,vd,vv,vh,zc,vi,vq,vj,xa,vk,wb,vl,xm,vm,wg,vn,wc,vo,wa,vp,xq,vq,yb,vr,wr,vs,xg,vt,ys,vv,zn,vw,zr,vx,ya,vz,yy,wa,zc,wb,wc,wc,xw,wd,yi,we,yx,wf,yf,wg,wv,wi,xl,wj,xo,wl,wp,wn,zc,wo,wt,wp,yv,wq,yf,wr,zs,ws,yt,wt,yi,wu,zb,wv,zb,wz,zr,xa,ym,xc,yd,xd,yc,xe,yc,xg,zc,xh,xl,xi,xk,xj,yg,xk,yt,xl,zt,xm,yf,xn,za,xo,yf,xp,zp,xq,yd,xr,yn,xw,xw,xx,zq,xy,zb,ya,zb,yb,zf,yc,zt,yd,yt,yf,yg,yg,zp,yh,yq,yi,zm,yk,zv,ym,yp,yn,yx,yo,yt,yp,zq,yq,zn,ys,zm,yt,yt,yv,zj,yw,zw,yx,zb,yy,za,za,zh,zb,zv,zc,zm,ze,zy,zf,zx,zh,zx,zj,zr,zm,zm,zn,zt,zp,zv,zq,zr,zr,zr,zs,zw,zt,zv,zv,zw,zw,zw,zx,zx,zy,zy


	public static void sop(Object a) {
		System.out.print(a.toString());
	}
	public static void sopl(Object a) {
		System.out.println(a.toString());
	}

	public static void sopl() {
		System.out.println();
	}
	
	public static int pint(String s) {
		if (IsNumber.isNumber(s)) {
			return Integer.parseInt(s);
		} else {
			sop("Error: (" + s + ") is not a number");
			return -1;
		}
	}
	

	public static long plong(String s) {
		if (IsNumber.isLong(s)) {
			return Long.parseLong(s);
		} else {
			sop("Error: (" + s + ") is not a number");
			return -1;
		}
	}
	
	public static void exit() {
		exit(0);
	}
	public static void exit(int code) {
		sop("Exit with code " + code);
		
		System.exit(code);
	}
	
	public static int[][] getIntTable(ArrayList<String> lines) {
		int grid[][] = new int[lines.size()][lines.get(0).length()];
		
		for(int i=0; i<lines.size(); i++) {
			
			for(int j=0; j<grid[0].length; j++) {
				if(lines.get(i).charAt(j) == '.') {
					grid[i][j] = -1;
				} else {
					grid[i][j] = (int)(lines.get(i).charAt(j) - '0');
				}
			}
		}
		
		return grid;
	}
	

	public static char[][] getCharTable(ArrayList<String> lines) {
		char grid[][] = new char[lines.size()][lines.get(0).length()];
		
		for(int i=0; i<lines.size(); i++) {
			
			for(int j=0; j<grid[0].length; j++) {
				grid[i][j] = lines.get(i).charAt(j);

			}
		}
		
		return grid;
	}

}
