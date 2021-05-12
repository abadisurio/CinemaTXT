// Generated by view binder compiler. Do not edit!
package com.abadisurio.cinematxt.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewpager.widget.ViewPager;
import com.abadisurio.cinematxt.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityHomeBinding implements ViewBinding {
  @NonNull
  private final CoordinatorLayout rootView;

  @NonNull
  public final CollapsingToolbarLayout collapseToolbar;

  @NonNull
  public final TabLayout tabs;

  @NonNull
  public final Toolbar toolbar;

  @NonNull
  public final ViewPager viewPager;

  private ActivityHomeBinding(@NonNull CoordinatorLayout rootView,
      @NonNull CollapsingToolbarLayout collapseToolbar, @NonNull TabLayout tabs,
      @NonNull Toolbar toolbar, @NonNull ViewPager viewPager) {
    this.rootView = rootView;
    this.collapseToolbar = collapseToolbar;
    this.tabs = tabs;
    this.toolbar = toolbar;
    this.viewPager = viewPager;
  }

  @Override
  @NonNull
  public CoordinatorLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityHomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.collapse_toolbar;
      CollapsingToolbarLayout collapseToolbar = rootView.findViewById(id);
      if (collapseToolbar == null) {
        break missingId;
      }

      id = R.id.tabs;
      TabLayout tabs = rootView.findViewById(id);
      if (tabs == null) {
        break missingId;
      }

      id = R.id.toolbar;
      Toolbar toolbar = rootView.findViewById(id);
      if (toolbar == null) {
        break missingId;
      }

      id = R.id.view_pager;
      ViewPager viewPager = rootView.findViewById(id);
      if (viewPager == null) {
        break missingId;
      }

      return new ActivityHomeBinding((CoordinatorLayout) rootView, collapseToolbar, tabs, toolbar,
          viewPager);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
