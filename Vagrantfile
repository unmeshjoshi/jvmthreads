# -*- mode: ruby -*-
# vi: set ft=ruby :
def create_synced_dir(config, host_dir, vm_dir, options = {})
  config.vm.synced_folder host_dir, vm_dir, options if File.directory?(host_dir)
end
# All Vagrant configuration is done below. The "2" in Vagrant.configure
# configures the configuration version (we support older styles for
# backwards compatibility). Please don't change it unless you know what
# you're doing.

Vagrant.require_version ">= 1.8.5"
Vagrant.configure(2) do |config|
  config.vm.box = "ubuntu/trusty64"
   config.vm.network "forwarded_port", guest:9092, host:9092
   config.vm.network "forwarded_port", guest:9093, host:9093
   config.vm.network "forwarded_port", guest:9094, host:9094
   config.vm.network "forwarded_port", guest:7999, host:7999
   config.vm.network "forwarded_port", guest:8081, host:8081
   config.vm.network "forwarded_port", guest:4000, host:4000
   config.vm.network "forwarded_port", guest:8500, host:8500

#zookeeper ports
   config.vm.network "forwarded_port", guest:2181, host:2181
   config.vm.network "forwarded_port", guest:2182, host:2182
   config.vm.network "forwarded_port", guest:2183, host:2183

  # rabbitmq
  config.vm.network "forwarded_port", guest: 5672, host: 5672

  config.vm.network "private_network", ip: "192.168.33.10"

  #
  config.vm.provider "virtualbox" do |vb|
      vb.memory = "4048"
      vb.cpus = 2
   end

  config.vm.provision "docker" do |docker|
  end
end
