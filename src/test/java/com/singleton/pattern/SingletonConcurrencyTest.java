package com.singleton.pattern;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;

class SingletonConcurrencyTest {

    @Test
    void doubleCheckedSingletonUsesSameInstanceAcrossThreads() throws Exception {
        assertSingleInstance(DoubleCheckedLockingSingleton::getInstance);
    }

    @Test
    void simulatedDatabaseSingletonUsesSameInstanceAcrossThreads() throws Exception {
        assertSingleInstance(DatabaseConnection::getInstance);
    }

    @Test
    void databaseDemoDoesNotRequireOrOpenJdbc() {
        DatabaseConnection db = DatabaseConnection.getInstance();
        db.disconnect();
        assertFalse(db.isConnected());
        assertThrows(IllegalArgumentException.class, () -> db.connect("  "));
        db.connect("jdbc:demo:example");
        assertTrue(db.isConnected());
        assertEquals("jdbc:demo:example", db.getConnectionString());
        db.disconnect();
    }

    private static <T> void assertSingleInstance(Callable<T> supplier) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(12);
        try {
            CountDownLatch start = new CountDownLatch(1);
            List<Future<T>> results = new ArrayList<>();
            for (int i = 0; i < 48; i++) {
                results.add(executor.submit(() -> {
                    start.await();
                    return supplier.call();
                }));
            }
            start.countDown();
            T first = results.get(0).get();
            for (Future<T> result : results) {
                assertSame(first, result.get());
            }
        } finally {
            executor.shutdownNow();
        }
    }
}
