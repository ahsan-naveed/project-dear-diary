$randInt = rand(0..100)
mutex = Mutex.new

printThread = Thread.new do 
    while true
        mutex.synchronize {
            if $randInt % 5 == 0
                puts "Divisible by 5: #{$randInt}"
                if $randInt % 5 != 0
                    puts "Not divisble by 5: #{$randInt}"
                end
            end
        }
    end
end

updateThread = Thread.new do 
    while true
        mutex.synchronize {
            $randInt = rand(0..100)
        }
    end
end

# Let the simulation run for 20 seconds
sleep(5)