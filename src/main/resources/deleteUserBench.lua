for i =1,#KEYS do
  redis.call("del",KEYS[i]);
  redis.log(redis.LOG_NOTICE, "key " .. KEYS[i]);
end

return true