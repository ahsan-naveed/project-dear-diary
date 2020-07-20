`cat /proc/versio`n => kernel-version
`cat /proc/cmdline` => command line args passed to the kernel during the boot process
`uname -r` => kernel-release
`lsblk` => block devices
`lsb_release -d` => descriptive kernel-version
`cat /etc/system-release` => kernel-version

---

### Messaging users
`write <user-name>`
write wall => sends message to all users you can pre-config a message like so 
1. first check if messaging is allowed with `mesg` => should output `is y`
2. second write message as `cat > message << END`
3. send the message to all active users with `wall message`
