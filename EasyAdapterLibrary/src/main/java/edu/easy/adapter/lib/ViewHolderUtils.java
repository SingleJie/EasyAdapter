package edu.easy.adapter.lib;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import java.lang.reflect.Field;

/**
 * ViewHolder 
 * @author Single
 * @category 反射实例化View
 * @version 1.1
 * @content <b>使用条件:</b><br>
 *  <b>1.实体类必须为外部类或静态内部类</b><br>
 *  <b>2.convertView.getTag()可以得到实例化后的对象</b>
 */
public class ViewHolderUtils {
	
	private static final String ID = "id";
	
	/**
	 * 利用反射机制实例化对象,主要运用在Adapter复用
	 * @param mContext 上下文对象
	 * @param convertView   复用的view <b>(Adapter getView 方法中的convertView)</b>
	 * @param layoutId 实例化布局ID
	 * @param cls ViewHolder <b>(你的实体类)</b>
	 * @return 装载后的view <b>(直接返回给getView)</b>
	 */
	public static View loadingConvertView(Context mContext,View convertView,int layoutId,Class<?> cls) 
	{
		try
		{
			if (null == convertView) 
			{
				convertView = LayoutInflater.from(mContext).inflate(layoutId,null);
				Object obj = cls.newInstance();
				Field[] mFields = obj.getClass().getDeclaredFields();

				for (Field mField : mFields) 
				{
		        	//得到资源ID
					int resourceId = mContext.getResources().getIdentifier(mField.getName(),ID,mContext.getApplicationContext().getPackageName());
					//允许访问私有属性
					mField.setAccessible(true);
					//保存实例化后的资源
					mField.set(obj,convertView.findViewById(resourceId));
				}
				convertView.setTag(obj);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return convertView;
	}

	/**
	 * 利用反射机制实例化对象 
	 * <br><b>可以在RecyclerView中使用,没有复用机制，因为RecyclerView自带了</b></br>
	 * <b>itemView.getContext()不能为空</b>
	 * @param itemView 需要被反射的view
	 * @param cls 反射后得到类对象
	 * @return
	 */
	public static <T>T loadingViewHolder(View itemView,Class<T> cls) 
	{
		Context mContext = itemView.getContext();
		Object obj = null;
		
		try 
		{
			obj = cls.newInstance();
			Field[] mFields = obj.getClass().getDeclaredFields();

			for (Field mField : mFields)
			{
				// 得到资源ID
				int resourceId = mContext.getResources().getIdentifier(mField.getName(),ID,mContext.getApplicationContext().getPackageName());
				// 允许访问私有属性
				mField.setAccessible(true);
				// 保存实例化后的资源
				mField.set(obj,itemView.findViewById(resourceId));
			}
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		return (T)obj;
	}
	
}