/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AField {
    public boolean isId() default false;
    public boolean ignored() default false;
    public String column() default "NaN";   // Column's Name
    public String sequence() default "NaN"; // Sequence's Name
    public String sequenceBefore() default ""; // Sequence's before
    public MyType type() default MyType.None;
}
