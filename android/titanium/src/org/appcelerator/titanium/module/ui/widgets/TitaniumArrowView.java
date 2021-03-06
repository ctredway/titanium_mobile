package org.appcelerator.titanium.module.ui.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.view.View;

public class TitaniumArrowView extends View
{

	private boolean leftArrow;
	private Path path;

	public TitaniumArrowView(Context context) {
		super(context);
		leftArrow = true;
		setFocusable(false);
		setFocusableInTouchMode(false);
		configureDrawable();
	}

	public void setLeft(boolean leftArrow) {
		this.leftArrow = leftArrow;
		configureDrawable();
	}

	private void configureDrawable() {
		path = new Path();

		if (leftArrow) {
			path.moveTo(0.0f, 1.0f);
			path.lineTo(1.0f, 2.0f);
			path.lineTo(1.0f, 0.0f);
			path.close();
		} else {
			path.lineTo(1.0f, 1.0f);
			path.lineTo(0.0f, 2.0f);
			path.lineTo(0.0f, 0.0f);
			path.close();
		}

		setWillNotDraw(false);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		setMeasuredDimension(getSuggestedMinimumWidth(), getSuggestedMinimumHeight());
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		if (path != null) {
			int w = getWidth()/2;
			int h = getHeight()/2;
			canvas.save();
			canvas.scale(w,h);
			if (!leftArrow) {
				canvas.translate(1, 0);
			}
			Paint p = new Paint();
			p.setAntiAlias(false);
			p.setARGB(175, 216, 216, 216);
			p.setStyle(Paint.Style.FILL);
			canvas.drawPath(path, p);
			p.setARGB(75, 0, 0, 0);
			p.setStrokeWidth(0.1f);
			p.setStrokeJoin(Join.ROUND);
			p.setStrokeCap(Cap.ROUND);
			p.setAntiAlias(true);
			p.setStyle(Paint.Style.STROKE);
			canvas.drawPath(path, p);
			canvas.restore();
		}
	}
}
