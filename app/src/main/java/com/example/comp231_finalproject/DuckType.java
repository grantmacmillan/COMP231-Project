package com.example.comp231_finalproject;

/**
 * Created by carson on 6/22/15.
 */
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Allows "duck typing" or dynamic invocation based on method signature rather
 * than type hierarchy. In other words, rather than checking whether something
 * IS-a duck, check whether it WALKS-like-a duck or QUACKS-like a duck.
 *
 * To use first use the coerce static method to indicate the object you want to
 * do Duck Typing for, then specify an interface to the to method which you want
 * to coerce the type to, e.g:
 *
 *   public interface Foo {
 *      void aMethod();
 *   }
 *   class Bar {
 *      ...
 *      public void aMethod() { ... }
 *      ...
 *   }
 *   Bar bar = ...;
 *   Foo foo = DuckType.coerce(bar).to(Foo.class);
 *   foo.aMethod();
 *
 *
 */
public class DuckType {

    private final Object objectToCoerce;

    private DuckType(Object objectToCoerce) {
        this.objectToCoerce = objectToCoerce;
    }

    private class CoercedProxy implements InvocationHandler {
        public Object invoke(Object proxy, Method method, Object[] args)
                throws Throwable {
            Method delegateMethod = findMethodBySignature(method);
            assert delegateMethod != null;
            return delegateMethod.invoke(DuckType.this.objectToCoerce, args);
        }
    }

    /**
     * Specify the duck typed object to coerce.
     *
     * @param object the object to coerce
     * @return
     */
    public static DuckType coerce(Object object) {
        return new DuckType(object);
    }

    /**
     * Coerce the Duck Typed object to the given interface providing it
     * implements all the necessary methods.
     *
     * @param
     * @param iface
     * @return an instance of the given interface that wraps the duck typed
     *         class
     * @throws ClassCastException if the object being coerced does not implement
     *             all the methods in the given interface.
     */
    public <T> T to(Class iface) {
        assert iface.isInterface() : "cannot coerce object to a class, must be an interface";
        if (isA(iface)) {
            return (T) iface.cast(objectToCoerce);
        }
        if (quacksLikeA(iface)) {
            return generateProxy(iface);
        }
        throw new ClassCastException("Could not coerce object of type "
                + objectToCoerce.getClass() + " to " + iface);
    }

    private  boolean isA(Class iface) {
        return objectToCoerce.getClass().isInstance(iface);
    }


    public  boolean quacksLikeA(Class iface) {
        for (Method method : iface.getMethods()) {
            if (findMethodBySignature(method) == null) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    private <T> T generateProxy(Class iface) {
        return (T) Proxy.newProxyInstance(iface.getClassLoader(),
                new Class[] { iface }, new CoercedProxy());
    }

    private Method findMethodBySignature(Method method) {
        try {
            return objectToCoerce.getClass().getMethod(method.getName(),
                    method.getParameterTypes());
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

}
