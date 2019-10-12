package com.example.ntbargainhunter;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.AndroidJUnitRunner;

import com.example.ntbargainhunter.HomeFragment;

import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MockitoTest extends AndroidJUnitRunner {
  @Override
  public Application newApplication(ClassLoader cl, String className, Context context)
      throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    return super.newApplication(cl, HomeFragment.class.getName(), context);
  }
}
