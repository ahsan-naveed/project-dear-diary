mutex = Mutex.new

mutex.lock()
mutex.lock()
puts "Hello"
mutex.unlock()
mutex.unlock()
