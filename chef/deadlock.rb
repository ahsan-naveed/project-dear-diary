mutexA = Mutex.new
mutexB = Mutex.new

t1 = Thread.new do
    puts "Acquiring Mutex A"
    mutexA.lock()
    sleep(1)
    mutexB.lock()
    puts "Thread t1 exiting"
end

t2 = Thread.new do 
    puts "Acquiring Mutex B"
    mutexB.lock()
    sleep(1)
    mutexA.lock()
    puts "Thread t2 exiting"
end

t1.join()
t2.join()