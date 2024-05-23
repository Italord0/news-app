package com.github.italord0.newsapp

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class RetryRule(private val retryCount: Int) : TestRule {
    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                var caughtThrowable: Throwable? = null

                // Retry logic
                for (i in 0 until retryCount) {
                    try {
                        base.evaluate()
                        return
                    } catch (t: Throwable) {
                        caughtThrowable = t
                        println("${description.displayName}: Test failed, retrying (${i + 1}/$retryCount)")
                    }
                }

                // If all retries fail, re-throw the last caught exception
                if (caughtThrowable != null) {
                    throw caughtThrowable
                }
            }
        }
    }
}