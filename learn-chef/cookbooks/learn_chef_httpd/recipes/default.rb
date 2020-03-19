package 'httpd'

service 'httpd' do
  action :start
  start_command '/usr/sbin/httpd -k start'
end

template '/var/www/html/index.html' do
  source 'index.html.erb'
end

