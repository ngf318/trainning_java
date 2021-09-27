package com.test.neil.kavayi.view;
/*
 * Copyright (C) 2016 Borja Bravo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.test.neil.kavayi.R;

public class ReadMoreTextView extends androidx.appcompat.widget.AppCompatTextView {
    private static final int DEFAULT_TRIM_LINES = 3;
    private static final int INVALID_END_INDEX = -1;
    private static final String ELLIPSIZE = "... ";

    private CharSequence text;
    private BufferType bufferType;
    private boolean readMore = true;
    private boolean collapse = false;
    private CharSequence trimCollapsedText;
    private CharSequence trimExpandedText;
    private ReadMoreClickableSpan viewMoreSpan;
    private int colorClickableText;

    private int lineEndIndex;
    private int trimLines;

    public ReadMoreTextView(Context context) {
        this(context, null);
    }

    public ReadMoreTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ReadMoreTextView);
        int resourceIdTrimCollapsedText =
                typedArray.getResourceId(R.styleable.ReadMoreTextView_trimCollapsedText, R.string.share_more);
        int resourceIdTrimExpandedText =
                typedArray.getResourceId(R.styleable.ReadMoreTextView_trimExpandedText, R.string.moment_less);
        this.trimCollapsedText = getResources().getString(resourceIdTrimCollapsedText);
        this.trimExpandedText = getResources().getString(resourceIdTrimExpandedText);
        this.trimLines = typedArray.getInt(R.styleable.ReadMoreTextView_trimLines, DEFAULT_TRIM_LINES);
        this.colorClickableText = typedArray.getColor(R.styleable.ReadMoreTextView_colorClickableText,
                ContextCompat.getColor(context, R.color.app_blue_3678c5));
        typedArray.recycle();
        viewMoreSpan = new ReadMoreClickableSpan();
        onGlobalLayoutLineEndIndex();
        setText();
    }

    public void setText() {
        try {
            super.setText(getDisplayableText(), bufferType);
            setMovementMethod(LinkMovementMethod.getInstance());
            setHighlightColor(Color.TRANSPARENT);
        } catch (StringIndexOutOfBoundsException e) {
            Log.e("nieaaa", e.toString());
        }
    }

    private CharSequence getDisplayableText() {
        return getTrimmedText(text);
    }

    private OnStateChanged mOnStateChanged;
    public interface OnStateChanged {
        void onStateChanged(boolean collapse);
    }

    private Runnable mContentClick;
    public void setContentClick(Runnable runnable) {
        mContentClick = runnable;
    }

    public void setCallback(OnStateChanged listener) {
        mOnStateChanged = listener;
    }

    public void setCollapse(boolean collapse) {
        this.readMore = collapse;
        this.collapse = collapse;
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        this.text = text;
        bufferType = type;
        setText();
    }

    private CharSequence getTrimmedText(CharSequence text) {
        refreshLineEndIndex();
        Log.d("nieaaa", "===1==========getTrimmedText" + lineEndIndex);
        if (text != null && lineEndIndex > 0) {
            if (readMore) {
                if (getLineCount() > trimLines || collapse) {
                    return updateCollapsedText();
                }
            } else {
                return updateExpandedText();
            }
        } else {
            Log.d("nieaaa", "===2==========getTrimmedText");
        }

        SpannableStringBuilder s = new SpannableStringBuilder(text, 0, text.length());
        addContentClickableSpan(s, "");
        return s;
    }

    private CharSequence updateCollapsedText() {
        collapse = true;
        Log.d("nieaaa", "=============updateCollapsedText");
        int trimEndIndex = lineEndIndex - (ELLIPSIZE.length() + trimCollapsedText.length());
        SpannableStringBuilder s = new SpannableStringBuilder(text, 0, trimEndIndex)
                .append(ELLIPSIZE)
                .append(trimCollapsedText);
        return addClickableSpan(s, trimCollapsedText);
    }

    private CharSequence updateExpandedText() {
        Log.d("nieaaa", "=============updateExpandedText");
        collapse = false;
        SpannableStringBuilder s = new SpannableStringBuilder(text, 0, text.length()).append(trimExpandedText);
        return addClickableSpan(s, trimExpandedText);
    }

    private void addContentClickableSpan(SpannableStringBuilder s, CharSequence trimText) {
        s.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                if (mContentClick != null) {
                    mContentClick.run();
                }
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
            }
        }, 0, s.length() - trimText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    private CharSequence addClickableSpan(SpannableStringBuilder s, CharSequence trimText) {
        addContentClickableSpan(s, trimText);
        s.setSpan(viewMoreSpan, s.length() - trimText.length(), s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return s;
    }

    private class ReadMoreClickableSpan extends ClickableSpan {
        @Override
        public void onClick(View widget) {
            readMore = !readMore;
            if (mOnStateChanged != null) {
                mOnStateChanged.onStateChanged(readMore);
            }
            Log.d("nieaaa", "=============ReadMoreClickableSpan");
            setText();
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(colorClickableText);
        }
    }

    private void onGlobalLayoutLineEndIndex() {
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ViewTreeObserver obs = getViewTreeObserver();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    obs.removeOnGlobalLayoutListener(this);
                } else {
                    obs.removeGlobalOnLayoutListener(this);
                }
                setText();
            }
        });

    }

    private void refreshLineEndIndex() {
        if (getLayout() == null) return;
        Log.d("nieaaa", "========refreshLineEndIndex = " + trimLines + " " + getLineCount());
        if (trimLines > 0) {
            if (getLineCount() >= trimLines) {
                lineEndIndex = getLayout().getLineEnd(trimLines - 1);
            }/* else if (collapse) {
                lineEndIndex = getLayout().getLineEnd(getLineCount() - 1);
            } */else {
                lineEndIndex = INVALID_END_INDEX;
            }

            if (text != null && text.length() < lineEndIndex) {
                lineEndIndex = INVALID_END_INDEX;
            }
        } else {
            lineEndIndex = INVALID_END_INDEX;
        }
    }
}