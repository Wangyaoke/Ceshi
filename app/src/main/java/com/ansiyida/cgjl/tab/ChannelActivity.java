package  com.ansiyida.cgjl.tab;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import com.ansiyida.cgjl.R;

import butterknife.ButterKnife;

/**
 * 频道管理
 * @Author RA
 * @Blog http://blog.csdn.net/vipzjyno1
 */
public class ChannelActivity extends Activity {
//	@Bind(R.id.finish_text)
//	TextView finish_text;
//	/** 用户栏目的GRIDVIEW */
//	private DragGrid userGridView;
//	/** 其它栏目的GRIDVIEW */
//	private OtherGridView otherGridView;
//	/** 用户栏目对应的适配器，可以拖动 */
//	DragAdapter userAdapter;
//	/** 其它栏目对应的适配器 */
//	OtherAdapter otherAdapter;
//	/** 其它栏目列表 */
//	ArrayList<ChannelItem> otherChannelList = new ArrayList<ChannelItem>();
//	/** 用户栏目列表 */
//	ArrayList<ChannelItem> userChannelList = new ArrayList<ChannelItem>();
//	private boolean flag=false;
//	/** 是否在移动，由于这边是动画结束后才进行的数据更替，设置这个限制为了避免操作太频繁造成的数据错乱。 */
//	boolean isMove = false;
//	private DbMannager mannager;
//	private Animation translate;
//	private BottomToTopFinishLayout finishLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//沉浸状态栏
		setContentView(R.layout.subscribe_activity);
		ButterKnife.bind(this);
//		initView();
//		initData();
	}
//
//
//	private void initView() {
//		mannager = DbMannager.getInstance();
//		userGridView = (DragGrid) findViewById(R.id.userGridView);
//		otherGridView = (OtherGridView) findViewById(R.id.otherGridView);
//		finishLayout = (BottomToTopFinishLayout) findViewById(R.id.root_view);
//		translate = AnimationUtils.loadAnimation(this, R.anim.anim_bottom_to_top);
//
//		finishLayout.setOnFinishListener(new BottomToTopFinishLayout.OnFinishListener() {
//			@Override
//			public void onFinish() {
//				ChannelActivity.this.finish();
////				overridePendingTransition(R.anim.defaul2, R.anim.anim_bottom_to_top);
//			}
//		});
//	}
//
//	private void initData() {
////		ChannelItem navigate = new ChannelItem();
////		navigate.setId(Integer.valueOf(maplist.get(i).get(SQLHelper.ID)));
////		navigate.setName(maplist.get(i).get(SQLHelper.NAME));
////		navigate.setOrderId(Integer.valueOf(maplist.get(i).get(SQLHelper.ORDERID)));
////		navigate.setSelected(Integer.valueOf(maplist.get(i).get(SQLHelper.SELECTED)));
////		list.add(navigate);
////		userChannelList = ((ArrayList<ChannelItem>)ChannelManage.getManage(MyApplication.getInstance().getSQLHelper()).getUserChannel());
////		otherChannelList = ((ArrayList<ChannelItem>)ChannelManage.getManage(MyApplication.getInstance().getSQLHelper()).getOtherChannel());
//		userChannelList = mannager.getChoiceItem("channel2");
//		otherChannelList = mannager.getDefaultItem("channel2");
////		userAdapter = new DragAdapter(this, userChannelList,userGridView.getFlag(),mannager,null);
//		userGridView.setAdapter(userAdapter);
//		otherAdapter = new OtherAdapter(this, otherChannelList);
//		otherGridView.setAdapter(this.otherAdapter);
//		//设置GRIDVIEW的ITEM的点击监听
//		otherGridView.setOnItemClickListener(this);
//		userGridView.setOnItemClickListener(this);
//	}
//
//
//	private  void setClickListener() {
//
//	}
//
//	/** GRIDVIEW对应的ITEM点击监听接口  */
//	@Override
//	public void onItemClick(AdapterView<?> parent, final View view, final int position,long id) {
//		//如果点击的时候，之前动画还没结束，那么就让点击事件无效
//		if(isMove){
//			return;
//		}
//
//		switch (parent.getId()) {
//			case R.id.userGridView:
//				boolean flag=userGridView.getFlag();
//				//position为 0，1 的不可以进行任何操作
//				if (!flag){
//					if (position != 0 && position != 1) {
//						final ImageView moveImageView = getView(view);
//						if (moveImageView != null) {
//							TextView newTextView = (TextView) view.findViewById(R.id.text_item);
//							final int[] startLocation = new int[2];
//							newTextView.getLocationInWindow(startLocation);
//							final ChannelItem channel = ((DragAdapter) parent.getAdapter()).getItem(position);//获取点击的频道内容
//							otherAdapter.setVisible(false);
//							//添加到最后一个
//							otherAdapter.addItem(channel);
//							new Handler().postDelayed(new Runnable() {
//								public void run() {
//									try {
//										int[] endLocation = new int[2];
//										//获取终点的坐标
//										otherGridView.getChildAt(otherGridView.getLastVisiblePosition()).getLocationInWindow(endLocation);
//										MoveAnim(moveImageView, startLocation , endLocation, channel,userGridView);
//										userAdapter.setRemove(position);
//									} catch (Exception localException) {
//									}
//								}
//							}, 50L);
//						}
//					}
//				}
//
//				break;
//			case R.id.otherGridView:
//				final ImageView moveImageView = getView(view);
//				if (moveImageView != null){
//					TextView newTextView = (TextView) view.findViewById(R.id.text_item);
//					final int[] startLocation = new int[2];
//					newTextView.getLocationInWindow(startLocation);
//					final ChannelItem channel = ((OtherAdapter) parent.getAdapter()).getItem(position);
//					userAdapter.setVisible(false);
//					//添加到最后一个
//					userAdapter.addItem(channel);
//					new Handler().postDelayed(new Runnable() {
//						public void run() {
//							try {
//								int[] endLocation = new int[2];
//								//获取终点的坐标
//								userGridView.getChildAt(userGridView.getLastVisiblePosition()).getLocationInWindow(endLocation);
//								MoveAnim(moveImageView, startLocation , endLocation, channel,otherGridView);
//								otherAdapter.setRemove(position);
//							} catch (Exception localException) {
//							}
//						}
//					}, 50L);
//				}
//				break;
//			default:
//				break;
//		}
//
//
//	}
//	@OnClick(R.id.finish_text)
//	public void finishText(){
//		userGridView.setFlag();
//		userAdapter.changeItem(userGridView.getFlag());
//	}
//	public void changeText(){
//		if (!userGridView.getFlag()){
//			finish_text.setText(R.string.tab_finish);
//		}else {
//			finish_text.setText(R.string.tab_edit);
//		}
//	}
//
//	@OnClick(R.id.delete_tab)
//	public void moveToTop(){
//		finishLayout.startAnimation(translate);
//		translate.setAnimationListener(new AnimationListener() {
//			@Override
//			public void onAnimationStart(Animation animation) {
//
//			}
//
//			@Override
//			public void onAnimationEnd(Animation animation) {
//				translate.cancel();
//				ChannelActivity.this.finish();
//				overridePendingTransition(R.anim.defaul2,R.anim.defaul2);
//
//			}
//
//			@Override
//			public void onAnimationRepeat(Animation animation) {
//
//			}
//		});
//
//	}
//
//	/**
//	 * 点击ITEM移动动画
//	 * @param moveView
//	 * @param startLocation
//	 * @param endLocation
//	 * @param moveChannel
//	 * @param clickGridView
//	 */
//	private void MoveAnim(View moveView, int[] startLocation,int[] endLocation, final ChannelItem moveChannel,
//						  final GridView clickGridView) {
//		int[] initLocation = new int[2];
//		//获取传递过来的VIEW的坐标
//		moveView.getLocationInWindow(initLocation);
//		//得到要移动的VIEW,并放入对应的容器中
//		final ViewGroup moveViewGroup = getMoveViewGroup();
//		final View mMoveView = getMoveView(moveViewGroup, moveView, initLocation);
//		//创建移动动画
//		TranslateAnimation moveAnimation = new TranslateAnimation(
//				startLocation[0], endLocation[0], startLocation[1],
//				endLocation[1]);
//		moveAnimation.setDuration(300L);//动画时间
//		//动画配置
//		AnimationSet moveAnimationSet = new AnimationSet(true);
//		moveAnimationSet.setFillAfter(false);//动画效果执行完毕后，View对象不保留在终止的位置
//		moveAnimationSet.addAnimation(moveAnimation);
//		mMoveView.startAnimation(moveAnimationSet);
//		moveAnimationSet.setAnimationListener(new AnimationListener() {
//
//			@Override
//			public void onAnimationStart(Animation animation) {
//				isMove = true;
//			}
//
//			@Override
//			public void onAnimationRepeat(Animation animation) {
//			}
//
//			@Override
//			public void onAnimationEnd(Animation animation) {
//				moveViewGroup.removeView(mMoveView);
//				// instanceof 方法判断2边实例是不是一样，判断点击的是DragGrid还是OtherGridView
//				if (clickGridView instanceof DragGrid) {
//					otherAdapter.setVisible(true);
//					otherAdapter.notifyDataSetChanged();
//					userAdapter.remove();
//				} else {
//					userAdapter.setVisible(true);
//					userAdapter.notifyDataSetChanged();
//					otherAdapter.remove();
//				}
//				isMove = false;
//			}
//		});
//	}
//
//	/**
//	 * 获取移动的VIEW，放入对应ViewGroup布局容器
//	 * @param viewGroup
//	 * @param view
//	 * @param initLocation
//	 * @return
//	 */
//	private View getMoveView(ViewGroup viewGroup, View view, int[] initLocation) {
//		int x = initLocation[0];
//		int y = initLocation[1];
//		viewGroup.addView(view);
//		LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//		mLayoutParams.leftMargin = x;
//		mLayoutParams.topMargin = y;
//		view.setLayoutParams(mLayoutParams);
//		return view;
//	}
//
//	/**
//	 * 创建移动的ITEM对应的ViewGroup布局容器
//	 */
//	private ViewGroup getMoveViewGroup() {
//		ViewGroup moveViewGroup = (ViewGroup) getWindow().getDecorView();
//		LinearLayout moveLinearLayout = new LinearLayout(this);
//		moveLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
//		moveViewGroup.addView(moveLinearLayout);
//		return moveLinearLayout;
//	}
//
//	/**
//	 * 获取点击的Item的对应View，
//	 * @param view
//	 * @return
//	 */
//	private ImageView getView(View view) {
//		view.destroyDrawingCache();
//		view.setDrawingCacheEnabled(true);
//		Bitmap cache = Bitmap.createBitmap(view.getDrawingCache());
//		view.setDrawingCacheEnabled(false);
//		ImageView iv = new ImageView(this);
//		iv.setImageBitmap(cache);
//		return iv;
//	}
//
//	/** 退出时候保存选择后数据库的设置  */
//	private void saveChannel() {
//		//ChannelManage.getManage(AppApplication.getApp().getSQLHelper()).deleteAllChannel();
//		//ChannelManage.getManage(AppApplication.getApp().getSQLHelper()).saveUserChannel(userAdapter.getChannnelLst());
//		//ChannelManage.getManage(AppApplication.getApp().getSQLHelper()).saveOtherChannel(otherAdapter.getChannnelLst());
//		userAdapter.saveSQL();
//		otherAdapter.saveSQL();
//	}
//
//	@Override
//	public void onBackPressed() {
//		saveChannel();
//		finishLayout.startAnimation(translate);
//		translate.setAnimationListener(new AnimationListener() {
//			@Override
//			public void onAnimationStart(Animation animation) {
//
//			}
//
//			@Override
//			public void onAnimationEnd(Animation animation) {
//				translate.cancel();
//				animation.cancel();
//				ChannelActivity.this.finish();
//				overridePendingTransition(R.anim.defaul2,R.anim.defaul2);
//			}
//
//			@Override
//			public void onAnimationRepeat(Animation animation) {
//
//			}
//		});
//	}


}
