package interviews.Wise;

import java.time.Instant;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.Collectors;

import java.time.Instant;
import java.time.Duration;
import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

abstract class Request {
    String host;
    abstract Response call(); // This makes request to external server
}
// You don't need to make any changes for this class
class Response {
    int status;
    String body;
}


class WebClient {
    private static final int MAX_FAILURES = 3;
    private static final long TIME_WINDOW = 10 * 60; // 10 minutes in seconds
    private static final long COOL_OFF_PERIOD = 5 * 60; // 5 minutes in seconds

    private Deque<Long> failureTimestamps = new ConcurrentLinkedDeque<>();
    private long lastBlockedTimestamp = 0;
    private String lastBlockedHost = null;

    public Response execute(Request request) throws Exception {
        long currentTime = Instant.now().getEpochSecond();
        String host = request.host;

        // Check if service is blocked
        if (isServiceBlocked(host, currentTime)) {
            throw new ServiceBlockedException("Service " + host + " is currently blocked");
        }

        try {
            Response response = request.call();

            // Reset if successful (2xx status code)
            if (isSuccessfulResponse(response)) {
                cleanupTimestamps(currentTime);
                return response;
            }

            // Record failure
            recordFailure(currentTime,host);
            return response;
        } catch (Exception e) {
            // Record failure for any exception
            recordFailure(currentTime,host);
            throw e;
        }
    }

    private boolean isServiceBlocked(String host, long currentTime) {
        return lastBlockedHost != null &&
                host.equals(lastBlockedHost) &&
                currentTime < lastBlockedTimestamp;
    }

    private void recordFailure(long currentTime,String host) {
        cleanupTimestamps(currentTime);
        failureTimestamps.addLast(currentTime);

        // Check if should block
        if (failureTimestamps.size() >= MAX_FAILURES) {
            lastBlockedTimestamp = currentTime + COOL_OFF_PERIOD;
            lastBlockedHost = host;
        }
    }

    private void cleanupTimestamps(long currentTime) {
        // Remove timestamps outside the time window
        while (!failureTimestamps.isEmpty() &&
                currentTime - failureTimestamps.peekFirst() > TIME_WINDOW) {
            failureTimestamps.pollFirst();
        }
    }

    private boolean isSuccessfulResponse(Response response) {
        return response.status >= 200 && response.status < 300;
    }

    public static class ServiceBlockedException extends Exception {
        public ServiceBlockedException(String message) {
            super(message);
        }
    }
}
/*

Key Improvements:
        1. Uses Deque for timestamp tracking
2. Simplified cleanup mechanism
3. Directly tracks failures within time window
4. Maintains chronological order of failures
5. Easy to understand and implement

Recommendations:
        - For this specific problem, Deque is more appropriate
- Provides a clean, time-based sliding window approach
- Matches the problem's requirement of "3 failures in 10 minutes"

The Deque approach is more aligned with the original implementation and the problem statement. It provides a straightforward way to track and manage time-based failures.

Would you like me to elaborate on any part of this implementation?
*/