
package com.example.ntbargainhunter;

import org.junit.Test;

import static org.junit.Assert.*;

import com.google.common.truth.Truth.assertThat;
import org.junit.Test;
import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

public class MockTestRunner extends AndroidJUnitRunner {
  @Override
  public Application newApplication(ClassLoader cl, String className, Context context)
      throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    return super.newApplication(cl, MyMockApplication.class.getName(), context);
  }
}
