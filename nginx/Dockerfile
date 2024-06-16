#FROM nginx
#COPY nginx.conf /etc/nginx/nginx.conf
#COPY start-nginx.sh /start-nginx.sh
#RUN chmod +x /start-nginx.sh
#CMD ["/start-nginx.sh"]

FROM nginx
RUN rm /etc/nginx/conf.d/default.conf
COPY nginx.conf /etc/nginx/conf.d/default.conf