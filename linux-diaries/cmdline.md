- `cat /proc/versio`n => kernel-version
- `cat /proc/cmdline` => command line args passed to the kernel during the boot process
- `uname -r` => kernel-release
- `lsblk` => block devices
- `lsb_release -d` => descriptive kernel-version
- `cat /etc/system-release` => kernel-version

---

### Messaging users
- `write <user-name>`
- write wall => sends message to all users you can pre-config a message like so 
  1. first check if messaging is allowed with `mesg` => should output `is y`
  2. second write message as `cat > message << END`
  3. send the message to all active users with `wall message`

### Starting and Stopping Centos
#### Shutdown commands
- `poweroff`
- `reboot`
- `halt`
- `init`
- `telinit`
- `shutdown -h --halt, -r --reboot, -c --cancel` ex: `shutdown -h 10 "The system is about to be shutdown in 10 mins"`
- shutdown generates `/run/nologin` file; which prohibits any non-root user to login when there is only 5 minutes left to shutdown
- `!$`: stands for last argument
- `!rm`: runs the last command that began with 'rm'
- `touch one/file{1..5}`
- `mkdir -m 777 d1` -> sets all permissions for user group and others -> verify via `ls -ld d1`

### Changing Runlevels & Setting Default
