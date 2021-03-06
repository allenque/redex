/**
 * Copyright (c) 2016-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */

package com.facebook.redex.test.proguard;

import java.util.List;

public class Delta {

  public static int alpha;
  private static int beta;
  private int gamma;

  public static boolean sigma = true;

  public Delta() {}
  public Delta(int i) {}
  public Delta(String s) {}

  public class A {}

  public class B {}

  public class C {
    int i;
    C() {}
    C(int i) {}
    public int iValue() { return i; }
  }

  public class D {
    int i;
    public int iValue() { return i; }
  }

  public class E {
    int i;
    public int iValue() { return 42; }
  }

  public class F {
    int wombat;
    final int numbat = 42;
    public int numbatValue() { return numbat; }
  }

  public class G {
    int wombat;
    public int wombatValue() { return wombat; }
  }

  public class H {
    int wombat ;
    boolean numbat;
    public int myIntValue() { return wombat; }
    public boolean myBoolValue() { return numbat; }
  }

  public class I {
    int wombat;
    int wombat_alpha;
    int numbat;
  }

  // Keep rule rule will match black_bear but not brown_bear because
  // $$ will not match against primitive types.
  // -keep class com.facebook.redex.test.proguard.Delta$J {
  //  ** *_bear;
  //  public *** alpha?;
  //  public ** beta*;
  //  public **[] gamma*;
  // }
  public class J {
     public J() {}
     public J(int i) {}
     public J(String s) {}
     public int brown_bear; // not kept, primitive type
     public String black_bear; // kept, class type
     public int[] grizzly_bear; // not kept, array type
     public String[] polar_bear; // not kept, array type
     public int alpha0; // kept by *** alpha?
     public int[] alpha1; // kept by *** alpha?
     public int[][] alpha2; // kept by *** alpha?
     public void alpha3() {}
     public int beta0; // not kept, primitive type
     public List<String> beta; // kept, class type
     public List<Integer>[] beta1; // not kept, array type
     public int[] gamma1; // not kept because ** does not match primtivie int
     public String[] gamma2; // kept because ** matches class and [] matches array
     public int omega(int int_arg, boolean bool_arg, String string_arg, char char_arg)
                { return int_arg + string_arg.length(); }
     public int omega(short s) { return 42; };
     public int omega(String s) { return s.length(); } // No keep rule, so pruned.
     // All thetas kept by (...)
     public int theta(int int_arg, boolean bool_arg, String string_arg, char char_arg)
                { return int_arg + string_arg.length(); }
     public int theta(short s) { return 42; };
     public int theta(String s) { return s.length(); }
     // No iotas should be matched.
     public int iota(int int_arg, boolean bool_arg, String string_arg, char char_arg)
                { return int_arg + string_arg.length(); }
     public int iota(short s) { return 42; };
     public int iota(short[] s) { return 42; };
     public int iota(String s) { return s.length(); }
     public void zeta0() {}
     public String zeta1() { return "and everything"; }
  }

  @DoNotStrip public class K {
     public int alpha;
     @DoNotStrip public int beta;
     public void gamma() {}
     @DoNotStrip public void omega() {}
  }

  public class L {
     public void alpha0() {}
     protected void alpha1() {}
     private void alpha2() {}
     public void beta0() {}
     protected void beta1() {}
     private void beta2() {}
     public void gamma0() {}
     protected void gamma1() {}
     private void gamma2() {}
  }

}
