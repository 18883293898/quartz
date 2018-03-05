for i =1,#KEYS do
  redis.log(redis.LOG_NOTICE, "key " .. KEYS[i]);
  redis.log(redis.LOG_NOTICE, "value " .. ARGV[i]);
  redis.call("set",KEYS[i],ARGV[i]);
end

return true