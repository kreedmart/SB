package ru.skdev.sb;

/**
 *
 * @author sergekos
 */
public class SingletoneDoubleCheckedLocking {
    private static volatile SingletoneDoubleCheckedLocking instance;

    public static SingletoneDoubleCheckedLocking getInstance() {
        if (instance == null) {
            synchronized(SingletoneDoubleCheckedLocking.class) {
                if (instance == null) {
                    instance = new SingletoneDoubleCheckedLocking();
                }
            }
        }

        return instance;
    }
}
